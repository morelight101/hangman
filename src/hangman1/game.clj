(ns game
  (:require [clojure.string :refer [split includes? starts-with?]]
            [clojure.set    :refer [subset?]]))

(defn game-won?
  "Liefert true, wenn der Spieler das Spiel `game `gewonnen hat, andernfalls false. "
  [{:keys [word-to-guess correct-guesses]}]
  (= (into #{} word-to-guess) correct-guesses))

(defn game-lost?
  "returns true if player lost, otherwise false"
  [{:keys [tries-left]}]
  (zero? tries-left))

(defn game-over?
  "returns true if user lost or won the game, otherwise false"
  [game]
  (or (game-won? game) (game-lost? game)))

(defn new-game
  "creates a new game map"
  [word-to-guess]
  {:word-to-guess  word-to-guess
   :correct-guesses '#{}
   :tries-left 5})

(defn guess-letter
  "returns an updated game reflecting whether the letter the user guessed was contained in the word-to-guess;
  is the game over, the function returns the game state without change"
  [game letter]
  (cond
    (game-over? game) game
    (includes?  (:word-to-guess game) letter)
    (update-in game [:correct-guesses] conj letter)
    :else     (update-in game [:tries-left] dec)))

(defn score
  "returns the current score"
  [{:keys [correct-guesses]}]
  (* 8 (count (:correct-guesses game))))

(defn hint
  "returns a vector of correctly guessed letters in correct position;
  not yet correctly guessed letters are indicated by nil"
  [{:keys [word-to-guess correct-guesses]}]
  (map
   #(if
        (includes? (apply str correct-guesses) %)
          %
          nil)
   (split word-to-guess #"\B" )))


