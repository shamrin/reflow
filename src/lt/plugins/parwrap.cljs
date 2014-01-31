(ns lt.plugins.parwrap
  (:require [lt.object :as object]
            [lt.objs.editor :as ed]
            [lt.objs.editor.pool :as pool]
            [lt.objs.command :as cmd]
            [lt.objs.files :as files])
  (:require-macros [lt.macros :refer [behavior defui]]))

(defn rewrap [text width]
  (let [text (.replace text (js/RegExp. "\\s*\n" "g") " ")]
    (_rewrap text width)))

(defn _rewrap [text width]
  (if (>= width (.-length text))
    text
    (let [re (js/RegExp. (str "^((.{0," (- width 1) "}\\S)\\s+(.*)|(\\S*)\\s*(.*))"))
          match (.exec re text)
          line (or (aget match 2) (aget match 4))
          more (or (aget match 3) (aget match 5))]
      (if (< 0 (.-length more))
        (str line "\n" (_rewrap more width))
        line))))

(behavior ::rewrap
          :triggers #{:rewrap}
          :reaction (fn [editor]
                      (if (ed/selection? editor)
                        (let [{:keys [from to]} (ed/selection-bounds editor)]
                          (ed/set-selection editor {:line (:line from) :ch 0} {:line (:line to)}))
                        (let [pos (ed/->cursor editor)
                              line (:line pos)
                              from (+ (or (first (filter #(= (ed/line-length editor %) 0) (range line 0 -1))) -1) 1)
                              to   (- (or (first (filter #(= (ed/line-length editor %) 0) (range line (+ (ed/last-line editor) 1))))
                                          (+ (ed/last-line editor) 1)) 1)]
                          (if (<= from to)
                            (ed/set-selection editor {:line from :ch 0} {:line to}))))
                      (if (ed/selection? editor)
                        (let [width (ed/option editor :parwrap-width)]
                          (ed/replace-selection editor (rewrap (ed/selection editor) width))))))

(behavior ::wrap-width
          :triggers #{:object.instant}
          :desc "Editor: paragraph wrap width"
          :params [{:label "Wrap width" :type :number}]
          :type :user
          :exclusive true
          :reaction (fn [editor width]
                      (ed/set-options editor {:parwrap-width width})))

(cmd/command {:command :parwrap-rewrap
              :desc "Rewrap paragraph"
              :exec (fn []
                      (when-let [editor (pool/last-active)]
                        (object/raise editor :rewrap)))})
