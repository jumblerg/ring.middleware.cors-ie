# ring.middleware.cors-ie [![Build Status][1]][2]

ring middleware that extends cross-origin resource sharing (cors) capabilities to ie 8, 9, and 10 via [eli grey's pmxdr library][3].

## installation

[![latest version][4]][5]

## application

thread cors-ie onto the ring stack before ring.middleware.cors in order to make use of [pmxdr-client.js][6].  the allowed origins should be passed to the [jumblerg/ring.middleware.cors][7] wrapper as described in that project's documentation.

```clojure
(require '[ring.middleware.cors    :refer [wrap-cors]])
(require '[ring.middleware.cors-ie :refer [wrap-cors-ie]])

(-> routes
  (wrap-cors-ie)
  (wrap-cors #".*localhost.*" #".*mydomain.org$"))
```

## license

copyright (c) jumblerg. all rights reserved.

distributed with clojure under the eclipse public license

[1]: https://travis-ci.org/jumblerg/ring.middleware.cors-ie.png?branch=master
[2]: https://travis-ci.org/jumblerg/ring.middleware.cors-ie
[3]: https://github.com/eligrey/pmxdr
[4]: https://clojars.org/jumblerg/ring.middleware.cors/latest-version.svg?bustcache=1.0.0
[5]: https://clojars.org/jumblerg/ring.middleware.cors-ie
[6]: https://github.com/eligrey/pmxdr/blob/master/pmxdr-client.js
[7]: https://clojars.org/jumblerg/ring.middleware.cors