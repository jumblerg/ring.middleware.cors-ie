(ns ring.middleware.cors-ie-test
  (:require
  	[clojure.test :refer :all]
    [ring.middleware.cors-ie :refer :all] ))

;;; utilities ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn get-header [res hdr] (-> res :headers (get hdr)))

;;; tests ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftest test-wrap-cors-ie
  (testing
    "if the uri corresponds to the pmxdr-host uuid return the javascript library."
    (let [req {:headers        {}
               :request-method :get
               :uri            "/fefdb2c0-eeb4-4914-9be2-97c4df709ee8.html"}
          res  {:status        200}
          ret ((wrap-cors-ie (fn [_] res)) req) ]
          (println :ret ret)
      (is (= (get-header ret "Content-Type") "text/html")) )))
