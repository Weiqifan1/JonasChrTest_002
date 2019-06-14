# JonasChrTest_002

2019-04-08:
exsempel på stiden til en lydbog:
https://librivox.org/robinson-crusoe-by-daniel-defoe/
librivox api:
https://librivox.org/api/info


#NEWS
2019-04-14
https://developer.android.com/distribute/best-practices/develop/target-sdk
Vi kan ikke uploade aps der targetter Api'er under 28 i fremtiden. Men goer vi det, kan jeg ikke bruge min tablet 
til at debugge, da den kun kan klare api 22 og mindre. :(
Jeg regner med at vi maa lave en ny app, hvis vi vil laegge appen paa app store.

2019-04-08:
Christian: forsøger at lave 003 og 004.
Jonas: kigger på hvordan man laver en afspiller.

Userstories:

001: finde filer på telefonen: 
Antagelse: vi har downloaded en fil og overført den til en mappe på telefonen.
Beskrivelse: når tester på telefonen, kan vi finde filnavnet (givet placeringen som vi kender)
og kan afspille filen.
Acceptance: ovenstående.


002: afspiller metode:
beskrivelse: vi laver en metode/klasse der afspiller en lydfil givet filnavn og filplacering.
den må gerne bruge en preinstalleret android afspiller.

003: fil oversigt layout:
Når man går ind i appen, får man en kanp. Når man trygger på den, 
får man en liste over alle lydfiler. 
Ved at trygge på et list-item, skal man kunne "vælge" den.

004: "gemme state":
når man går ind i appem igen, skal appen huske hvilken fil man arbejde med og
hvor langt man var nået.

005: afspildning af valgt fil.
forudsætning: 004 og 002 er løst.
Beskrivelse: når man har valgt en lydfil, kan vores afspildningsmetode afspille den.
Vores app skal være stylet sådan at appen ikke går i vejen for telefonens præ-installerede
afspiller.

006: udvidelse af telefonens almindelige afspiller:
Liste af features (betragt hver af dem som en usrstory):
A: 5 sec frem, 5 tilbage
B: 1 min frem, 1 min tilbage
C: 5 min frem, 5 tilbage.
D: "bookmark" (skal kunne gemmes efter man har forladt lydfilen)
E: "buffer knap": stopper automatisk appen efter en time, og laver automatisk et bookmark. (se også 004)


007: knap med liste og beskrivelser over bookmarks

