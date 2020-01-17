Generator VCard dla pracowników PŁ.

Sposób użycia:
Po uruchomieniu servera i wejściu na http://localhost:8080/ wyświetli się okienko do wpisania imienia i nazwiska żądanego pracownika PŁ. Naciśnięcie przycisku "Search" wygeneruje listę znalezionych pracowników, obok każdego z nich wyświetli się przycisk "Wygeneruj VCard", którego kliknięcie spowoduje rozpoczęcie pobierania pliku vcf o nazwie wybranego pracownika.

METODA			ŻADANIE			ODPOWIEDŹ
GET			/			Wyświetla stronę z formularzem i przyciskiem SEARCH
GET			/search?name		Wyświetla listę pracowników PŁ podanych w parametrze name
GET			/download/{fullName}	Generuje VCard pracownika dla imienia i nazwiska podanego jako parametr