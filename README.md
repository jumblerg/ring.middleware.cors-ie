# ring.middleware.cors-ie [![Build Status][1]][2]

ring middleware that extends cross-origin resource sharing (cors) capabilities to internet explorers 8, 9, and 10 via [eli grey's pmxdr library][3].

## installation

[![latest version][4]][5]

## application

thread the cors-ie middleware, along with ring.middleware.cors, onto the ring stack in order to serve the html required by [pmxdr-client.js][6]. the allowed origins should be passed to the [ring.middleware.cors][7] wrapper as described in that project's documentation.  configure pmxdr-client.js to request the pmxdr-host at `/fefdb2c0-eeb4-4914-9be2-97c4df709ee8.html`.

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
[4]: https://clojars.org/jumblerg/ring.middleware.cors-ie/latest-version.svg?bustcache=1.0.0-1
[5]: https://clojars.org/jumblerg/ring.middleware.cors-ie
[6]: https://github.com/eligrey/pmxdr/blob/master/pmxdr-client.js
[7]: https://github.com/jumblerg/ring.middleware.cors