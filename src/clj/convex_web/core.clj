(ns convex-web.core
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(defmacro shapes []
  (edn/read-string (slurp (io/resource "paths.edn"))))
