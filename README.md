### Breakout to klasyczna gra zręcznościowa, w której gracz kontroluje platformę do odbijania piłki, niszcząc kolejne bloki na ekranie, aby zdobywać punkty i przechodzić poziomy. Celem jest utrzymanie piłki w grze, unikając, by spadła poniżej platformy. Projekt swtorzony przy użyciu biblioteki JavaFX



https://github.com/KrzysztofChec/Breakout_game/assets/126595487/d29569c5-c5cb-4621-972d-9069d1557667



## Ball.java
Klasa `Ball` reprezentuje piłkę w grze breakout. Jest odpowiedzialna za zarządzanie jej ruchem, odbiciami od paletki i cegieł, oraz sprawdzanie kolizji z innymi elementami na planszy. Wykorzystuje bibliotekę JavaFX do renderowania grafiki i obsługi interakcji z użytkownikiem.

## Brick.java
`Brick` jest klasą reprezentującą cegłę w grze breakout. Cegiełki te mogą być zbite przez piłkę, co prowadzi do zwiększenia wyniku gracza. Ta klasa wykorzystuje JavaFX do rysowania i obsługi grafiki.

## GameCanvas.java
Klasa `GameCanvas` jest głównym obszarem gry, w którym odbywa się logika i renderowanie. Zarządza ona wszystkimi elementami gry, takimi jak paletka, piłka i cegły. Wykorzystuje bibliotekę JavaFX do renderowania grafiki, obsługi interakcji z użytkownikiem oraz do animacji.

## GraphicsItem.java
`GraphicsItem` jest abstrakcyjną klasą bazową dla wszystkich elementów graficznych w grze. Zawiera podstawowe informacje o położeniu i rozmiarze, oraz wymusza implementację metody `draw`, która jest używana do renderowania danego elementu na ekranie.

## HelloApplication.java
Klasa `HelloApplication` jest główną klasą aplikacji. Odpowiada za uruchamianie gry, inicjowanie interfejsu użytkownika oraz obsługę głównego okna. Wykorzystuje bibliotekę JavaFX do tworzenia interfejsu użytkownika oraz do uruchomienia gry.

## Paddle.java
Klasa `Paddle` reprezentuje paletkę w grze breakout. Gracz kontroluje jej położenie, aby odbijać piłkę i zapobiegać jej spadaniu poza planszę. Wykorzystuje bibliotekę JavaFX do obsługi grafiki i interakcji z użytkownikiem.

Te klasy współpracują ze sobą, tworząc pełną funkcjonalność gry breakout. Wykorzystanie biblioteki JavaFX umożliwia łatwe renderowanie grafiki oraz interakcję z użytkownikiem, co przyczynia się do realizacji założeń gry. Jeśli masz dodatkowe pytania lub potrzebujesz więcej informacji, śmiało pytaj!
