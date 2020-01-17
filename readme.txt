Generator VCard.

Uruchamiając serwer trzeba wejść na link http://localhost:8080/ gdzie wyświetli sie okeinko do wpisania imienia lub nazwiska, bądź obu. Naciśnięcie przycisku "Search" spowoduje utworzenie listy pracowników z podanymi kryteriami. Przycisk "Wygeneruj VCard spowodouje pobranie pliku na dysk lokalny użytkownika. 

METODA			ŻADANIE			ODPOWIEDŹ
GET			/			Wyświetla stronę z polem tekstowym i przyciskiem SEARCH
GET			/search?name		Wyświetla listę pracowników Politechniki Łódzkiej
GET			/download/{fullName}	Generuje VCard pracownika dla imienia i nazwiska podanego