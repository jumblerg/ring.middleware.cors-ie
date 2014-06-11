(ns ring.middleware.cors-ie
  (:require 
    [clojure.java.io :refer [resource]] ))

;;; constants ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def js (slurp (.getPath (resource "js/pmxdr-host.js"))))
(def uri "/fefdb2c0-eeb4-4914-9be2-97c4df709ee8")

;;; private ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn serve-pmxdr-host-js [req handler]
  (let [mergeh #(update-in %1 [:headers] merge %2)
        uri?   (= (req :uri) uri) ]
    (let [res (handler req)]
      (if uri? (-> res (assoc :body js) (mergeh {"Content-Type" "application/javascript"}))) )))

;;; public ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn wrap-cors-ie [handler]
  "Ring middleware that extends cross-origin resource sharing (CORS) semantics to Internet Explorers
  8, 9 and 10 when used in conjuction with the jumblerg/ring.middleware.cors wrapper and Eli Grey's 
  pmxdr-client library at https://github.com/eligrey/pmxdr/blob/master/pmxdr-client.js."
  (fn [req] (serve-pmxdr-host-js req handler) ))
