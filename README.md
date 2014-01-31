Reflow
======
Reflow is a Light Table plugin that allows you to reflow paragraphs to a
fixed with.  It is available in the Light Table plugin repository, or
you can clone this repository into your plugins folder.

Usage
-----
Reflow provides a new command, called *Reflow paragraph*, that is bound
to `Ctrl`-`J` by default.  Without a selection, it acts on the current
paragraph, with paragraphs being delimited by blank lines.  With a
selection, it acts on all the lines that are part of the selection
(including unselected text on those lines).  The width of the reflowed
paragraph is set in your `user.behaviors` file:
```clojure
{:+ {:editor [(:lt.plugins.reflow/set-width 72)]}}
```

Whitespace is maintained, except at the ends of lines.  All whitespace
at the end of a line is compressed to a single space before the reflow,
and no whitespace is left at the ends of lines after the reflow.

License
-------
Reflow is distributed under the MIT license:

Copyright 2014 Robert Schroll

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
