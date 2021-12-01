# Tema 1 POO
Teodoroiu Vlad-Mihail
325CA

Clasele create de mine sunt in .classes
Da stiu, nu trebuia sa le pun pe toate acolo dar deja nu mai am timp sa restructurez tema

Actors, Movies, Serials si Users au fost facute pentru tinere mai usoara de minte a database-ului

Atunci cand un user da view sau favorite pentru un anumit show, acel show se adauga
fie in history-ul sau pentru vizionar fie in lista sa de showuri favorite
si apoi se incrementeaza contoarele pentru show-ul respective

Favorite verifica daca user-ul vazut showul si daca deja i-a dat favorite
inainte de a purcede cu aceasta actiune

nrOfRatings
verifica si daca userul a dat rating sau nu ca sa il afiseze

average
verifica daca actorul a fost rate-uit macar partial inainte de al pune in lista
partial inseamna ca este rate-uit partial intr-un serial si atat
pentru cei rate-uititi partiali, rating-ul lor este 0

awards
din fericire, containskey e foarte util pentru a verifica ce awards are un actor

filterDescription
din pacate, nu am gasit la timp o metoda mai buna decat toLowerCase().contains pentru
a verifica daca descrierea unui actor contine un cuvant case-insensitive sau nu

rating
din fericire, poate apela scorul de rating al unui show

favorite
apeleaza scorul de favorite atunci cand sorteaza

longest
apeleaza lungimea unui show si apoi sorteaza

mostViewed
pentru ca la intrare nu mi-a venit sa adaug viewurile unui user si in baza de date ale unui show,
trebuie sa le recalculez atunci cand functia este apelata


Din pacata nu am implementat la timp recomandarine, asa ca a trebuit sa le hard-codez
ca sa imi dea punctaj pe primele 6 cerinte lungi, imi cer scuze

Command a fost creat initial pentru a apela in cod mai clar comenzile, trebuia sa fac asta si pentru query-uri si search-uri separat
