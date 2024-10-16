# SEW Worttrainer Reloaded

Autor: Felix Dahmen

## Beschreibung des Projekts

Dieses Repository beinhaltet eine WortTrainer Applikation, die dazu dient, Vokabeln zu lernen.
Die Applikation wurde im Rahmen des SEW-Unterrichts erstellt und ist in Java programmiert.

## Architektur

Die Applikation besteht aus einer MVC-Struktur.

1. ApplicationController: Das ist der Startpunkt und Controller der Applikation, der Model und View steuert.
2. Model: Das Model beinhaltet die Daten und die Logik der Applikation. Die WordTrainer Klasse und WordImagePair Klasse dienen zu diesem zweck.
3. View: Die View beinhaltet die Benutzeroberfläche der Applikation. Die WordTrainerView Klasse ist die View.

4. persistence: Das sind die Klassen und Interfaces, die dazu dienen, das Model zu speichern und zu laden.
