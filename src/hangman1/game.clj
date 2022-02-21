(ns game
  (:require [clojure.string :refer [split]]))

(defn game-won?
  "Liefert true, wenn der Spieler das Spiel `game `gewonnen hat, andernfalls false. "
  [{:keys [word-to-be-guessed correct-guesses]}]
  (= (into #{} word-to-be-guessed) correct-guesses))

(defn game-lost?
  "returns true if player lost, otherwise false"
  [{:keys [tries-left]}]
  (zero? tries-left))

(defn game-over?
  "returns true if user lost or won the game, otherwise false"
  [game]
  (or (game-won? game) (game-lost? game)))

;; @Nico: Die Funktion ist nicht pure. Du möchtest als Programmierer, der diese Schicht nutzt, ein neues Spiel anlegen können. Wie kann das pure gehen?
;; @Ingo
;; pure bedeutet: no side-effects + same input -> same output
;; Irgendwo muss aber das word-to-guess ändern;
;; Vorschlag 1: insert-rand-word! wird nach new-game aufgerufen
;; Vorschlag 2: word-to-guess aus dem game auslagern
;; Warum Vorschlag 1 ?
;; Daten die zusammengehören, sollen zusammenbleiben zwecks Verständlichkeit für den Programmierer.
;; Pure functions sollen gleichfalls das Verständnis erleichtern, deshalb
;; dezidierter (= easily testable) Zustand zu Beginn, der anschliessend
;; impure wird. 

(defn new-game
  "creates a new game map"
  []
  {:word-to-guess "Test"
   :correct-guesses '#{}
   :tries-left 5})

(defn insert-rand-word!
  "selects a random word and makes it available to the game"
  [game]
  (assoc-in game  [:word-to-guess] (nth ["Palm" "Tree" "Vacation"] (rand-int 3))))


















