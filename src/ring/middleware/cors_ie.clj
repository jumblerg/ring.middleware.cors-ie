(ns ring.middleware.cors-ie
  (:require 
    [clojure.java.io :refer [resource]] ))

;;; constants ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def uri "/fefdb2c0-eeb4-4914-9be2-97c4df709ee8.html")
(def js (slurp (.getPath (resource "js/pmxdr-host.js"))))
(def html (str "<!DOCTYPE html><html><head><script type=\"text/javascript\">" js "</script><body></body></html>"))

;;; private ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn serve-pmxdr-host-js [req handler]
  (let [mergeh #(update-in %1 [:headers] merge %2)
        uri?   (= (req :uri) uri) ]
    (let [res (handler req)]
      (if uri? (-> res (assoc :body html) (mergeh {"Content-Type" "text/html"}))) )))

;;; public ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn wrap-cors-ie [handler]
  "Ring middleware that extends cross-origin resource sharing (CORS) semantics to Internet Explorers
  8, 9 and 10 when used in conjuction with the jumblerg/ring.middleware.cors wrapper and Eli Grey's 
  pmxdr-client library at https://github.com/eligrey/pmxdr/blob/master/pmxdr-client.js."
  (fn [req] (serve-pmxdr-host-js req handler) ))
