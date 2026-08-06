# CityBuildV2

CityBuildV2 ist ein **Spigot/Paper-Plugin** (Minecraft `1.19`) für den Betrieb eines CityBuild-/PlotSquared-Servernetzwerks. Es bündelt Moderations-Tools, Komfort-Commands für Spieler sowie mehrere Spielmechaniken (Booster, Food-Punkte, Farmwelt, Nether, Statusnachrichten, u. v. m.) in einem Plugin und speichert seine Daten über eine SQL-Datenbank.

> Dieses Repository ist ein internes/privates Plugin-Projekt von JuByte und hängt von mehreren privat gehosteten Bibliotheken ab (siehe [Abhängigkeiten](#abhängigkeiten)). Ohne Zugriff auf die entsprechenden Repositories lässt sich das Projekt **nicht** bauen.

## Features

Das Plugin stellt u. a. folgende Befehle bereit (je nach `config.yml` einzeln aktivierbar):

| Kategorie | Befehle |
|---|---|
| Moderation | `startkick`, `unstartkick`, `mutep`, `unmutep`, `commandspy`, `sudo`, `invsee`, `clearchat` |
| Spieler-Komfort | `back`, `teleport`/`tp`, `tphere`, `spawn`, `fly`, `speed`, `heal`, `feed`, `god`, `gamemode`, `glow`, `vanish`, `head` |
| Welt/Plot | `breakblock`, `farmworld`, `nether`, `signedit`, `checkplot` |
| Sonstiges | `status`, `giftrank`, `booster`, `food`, `slowchat`, `allornothing` (`ja`/`nein`) |

Alle Namen, Aliase und Aktivierungs-Flags lassen sich in `config.yml` anpassen; alle Nachrichten und Berechtigungsnodes in `messages.yml`.

## Voraussetzungen

- **Java 16** (siehe `pom.xml`, `maven.compiler.source`/`target`)
- **Maven 3.6+**
- Ein Spigot/Paper-Server `1.19-R0.1-SNAPSHOT`
- Zugriff auf die privaten Maven-Repositories (siehe unten), da `DeveloperAPI`, `pretronicdatabasequery` und `pretroniclibraries` nicht öffentlich verfügbar sind
- Eine erreichbare **MySQL-Datenbank** (oder kompatibler Dialekt) für den produktiven Betrieb
- Optionale Softdependencies für vollen Funktionsumfang: `PlotSquared`, `ProtocolLib`, `AutoUpdaterAPI`, `JuByteLibraries`

## Abhängigkeiten

| Bibliothek | Zweck |
|---|---|
| `spigot` (1.19) | Server-API |
| `com.jubyte.developerapi:DeveloperAPI` | Command-Framework, Config-/Message-Handling |
| `net.pretronic.databasequery:pretronicdatabasequery-sql` | SQL-Query-Builder/ORM (verhindert SQL-Injection, siehe unten) |
| `net.pretronic.libraries:pretroniclibraries-caching` | In-Memory-Caches für Spielerdaten |
| `com.intellectualsites.plotsquared:plotsquared-core` | Plot-Integration |
| `net.dmulloy2:ProtocolLib` | Packet-basiertes Sign-Editing |
| `org.projectlombok:lombok` | Getter-Generierung |
| `org.bstats:bstats-bukkit` | anonyme Nutzungsstatistik |

Diese werden über die in `pom.xml` hinterlegten Repositories (`repo.jubyte.com`, `repo.papermc.io`, `repo.codemc.org`, `maven.enginehub.org`, `repo.dmulloy2.net`) bezogen.

## Build

```bash
git clone <repo-url>
cd CityBuildV2
mvn clean package
```

Das fertige Plugin liegt danach unter `target/CityBuildV2-<version>.jar` (Shaded-Jar via `maven-shade-plugin`, `org.bstats` wird nach `com.jubyte` relokiert, um Konflikte mit anderen Plugins zu vermeiden).

## Installation

1. `CityBuildV2-<version>.jar` in den `plugins`-Ordner des Servers kopieren.
2. Server einmal starten, damit `config.yml`, `messages.yml` und `mysql.yml` im Plugin-Datenordner (`plugins/CityBuildV2/`) erzeugt werden.
3. `mysql.yml` mit den echten Zugangsdaten der Zieldatenbank befüllen (siehe [Konfiguration](#konfiguration) und [Sicherheitshinweise](#sicherheitshinweise)).
4. Server neu starten oder `/reload` verwenden.

## Konfiguration

- **`config.yml`** – Name/Aliase/Aktivierung jedes einzelnen Befehls.
- **`messages.yml`** – alle Spielernachrichten, Präfix sowie Permission-Nodes pro Befehl (Format `citybuild.<befehl>.<aktion>`).
- **`mysql.yml`** – Datenbankverbindung:

  ```yaml
  MySQL:
    dialectName: 'H2Portable'   # oder 'MySQL', je nach genutztem Dialekt
    Connection: 'CityBuildV2'
    Host: 'localhost'
    Port: 3306
    Database: 'CityBuildV2'
    User: 'root'
    Password: 'CHANGE_ME'
    useSSL: false
  ```

  > Die im Repository ausgelieferte `mysql.yml` enthält lediglich **Platzhalter-Zugangsdaten** (`root` / `Test`) als Vorlage. Diese Datei niemals mit echten Produktivzugangsdaten committen – die tatsächliche Konfiguration entsteht erst im Plugin-Datenordner auf dem Server und gehört **nicht** ins Git-Repository.

Fehlende Schlüssel werden beim Start automatisch aus den mitgelieferten Default-Dateien nachgetragen (`setConfigKeys()` / `setMessagesConfigKeys()` / `setMySQLConfigKeys()`), bestehende Werte bleiben dabei erhalten.

## Sicherheitshinweise

- Alle Datenbankzugriffe laufen über den `pretronicdatabasequery`-Query-Builder (`.where(...)`, `.set(...)`) statt über String-Verkettung – dadurch ist das Plugin durchgängig gegen klassische SQL-Injection abgesichert.
- Berechtigungen (`hasPermission(...)`) werden nicht über `plugin.yml` mit Default-Werten deklariert, sondern ausschließlich über ein externes Permission-Plugin (z. B. LuckPerms) vergeben. Ohne ein solches Plugin greifen serverseitig die Bukkit-Standards (i. d. R. nur OP).
- Sudo-, Invsee-, God- und CommandSpy-Befehle sind mächtige Admin-Tools – die zugehörigen Permission-Nodes sollten ausschließlich an vertrauenswürdiges Team-Personal vergeben werden.
- `mysql.yml` **niemals** mit echten Zugangsdaten versionieren (siehe oben).

## Bekannte gefundene und behobene Probleme

Im Rahmen einer Code-Durchsicht wurden folgende Fehler identifiziert und in diesem Repository bereits behoben:

1. **`CommandSpyCommand`** – Wurde der Befehl von der Konsole statt einem Spieler ausgeführt, fehlte ein `return` nach der Fehlermeldung, wodurch anschließend eine `ClassCastException` beim Cast auf `Player` auftrat.
2. **`TeleportCommand`** – Die Prüfung der Koordinaten-Grenzen (`-3.0E7`/`3.0E7`, Weltgrenze) nutzte `||` statt `&&` und war dadurch praktisch immer `true`; Teleports weit außerhalb der Weltgrenze waren möglich. Zusätzlich fehlte eine Behandlung für ungültige (nicht-numerische) Koordinateneingaben (`NumberFormatException`).
3. **`AsyncPlayerChatListener`** – Eine Debug-Ausgabe (`System.out.println("Keine Rechte")`) war im Produktivcode verblieben; außerdem konnte ein `assert`, das standardmäßig zur Laufzeit deaktiviert ist, eine `NullPointerException` bei unbekannten Offline-Spielern nicht verhindern.
4. **Speicherleck bei `GodCommand` / `CommandSpyCommand`** – Ausgeloggte Spieler wurden nie aus der `playerGodList` bzw. der CommandSpy-`Map` entfernt; die Referenzen blieben dauerhaft im Speicher und führten bei erneutem Login zu inkonsistentem Zustand. Wird jetzt in `PlayerQuitListener` bereinigt.
5. **`VanishCommand` war komplett funktionslos** – der Befehl war nirgends registriert, referenzierte nicht existierende Config-/Message-Schlüssel und enthielt keinerlei Vanish-Logik. Der Befehl wurde vollständig neu implementiert (Sichtbarkeits-Toggle inkl. Bereinigung beim Logout) und in `CityBuildV2#loadCommands()` registriert; die fehlenden Einträge in `config.yml`/`messages.yml` wurden ergänzt.

### Bekannte, noch offene Punkte

- **`BreakblockCommand`**: Die verschachtelte Logik prüft nach einem bereits als „PlotSquared vorhanden“ bestätigten Zweig erneut `getPlugin("PlotSquared") == null` – dieser Zweig ist dadurch unerreichbar (toter Code). Zusätzlich wird eine `PlotArea` über `String.valueOf(y)` mit dem Weltnamen verglichen, was mit hoher Wahrscheinlichkeit nie zutrifft. Eine Korrektur erfordert Kenntnis der exakt genutzten PlotSquared-API-Version und wurde daher nicht blind verändert – vor einer Anpassung sollte die gewünschte Fachlogik (welche Welten/Areas ein Blockabbau ohne Plot-Prüfung erlauben) geklärt werden.
- Es existiert kein `permissions:`-Block in `plugin.yml`; alle Rechte werden ausschließlich über ein externes Permission-Plugin vergeben. Für Server ohne ein solches Plugin sind viele Befehle faktisch nur für Operatoren nutzbar.

## Projektstruktur

```
src/main/java/com/jubyte/citybuild/
├── CityBuildV2.java          # Plugin-Einstiegspunkt (onEnable/onDisable, Registrierung)
├── commands/                 # Ein Command pro Datei (+ Subcommands für farmworld/nether)
├── listener/                 # Bukkit-Event-Listener (block, entity, inventory, player)
├── manager/                  # In-Memory-Caches je Spieler-Datentyp (Cooldown, Glow, Status, …)
├── storage/                  # SQL-Zugriffsschicht (Query-Builder, keine rohen SQL-Strings)
├── data/                     # Zentraler Zugriff auf Config-/Message-Werte als statische Felder
└── utils/                    # Hilfsklassen (u. a. PlotSquared-Utils, Sign-Editing)
```

## Mitwirkende

- JuByte
- Justin_SGD
- KeinByte
- Forumat
