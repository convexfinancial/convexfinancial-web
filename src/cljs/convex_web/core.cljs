(ns convex-web.core
  (:require [reagent.core :as reagent]
            [reanimated.core :as anim])
  (:require-macros [convex-web.core :refer [shapes]]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Vars

(defonce app-state
  (reagent/atom {}))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Page

(defn logo [size]
  (let [{:keys [inner outer]} (shapes)
        outer-path [:path {:d outer}]
        inner-path [:path {:d inner}]
        light-blue "#9ee8e3"
        medium-blue "#73abab"
        pink "#ef6d6d"
        rotate (fn [ang]
                 (str "rotate(" ang ",92,92)"))]
    [:svg {:width  (str size "px")
           :height (str size "px")
           :viewBox "0 0 184 184"}
     (into [:g]
           (for [ang [0 -90 -180]]
             ^{:key (rand)}
             [:g {:transform (rotate ang)
                  :fill light-blue}
              outer-path]))
     (into [:g
            (for [[ang col] [[0 pink]
                             [90 medium-blue]
                             [180 medium-blue]]]
              ^{:key (rand)}
              [:g {:fill col
                   :transform (rotate ang)}
               inner-path])])]))

(defn page [ratom]
  (let [size        (reagent/atom 64)
        size-spring (anim/spring size)]
    (fn [ratom]
      [:div.wrapper
       [:div.panel-1 {:on-click #(swap! size + 10)}
        [logo @size-spring]
        [:div.blurb
         [:p [:strong "Convex Financial"]
          " provides specialised solutions for financial services companies. We help our partners manage the economics of products and distribution channels, and help implement innovative retail advice and distribution models."]]]
       [:div.panel-2
        [:small "Convex Financial"]]])))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialize App

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")))

(defn reload []
  (reagent/render [page app-state]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (reload))
