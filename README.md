# Build
Dieses Plugin schützt die Welt vor jeglicher permanenter Veränderung durch Spieler, Entities oder natürliche Ursachen. 
Spieler mit der zugehörigen Berechtigung können in den Build-Modus wechseln, in dem sie die Welt im Kreativmodus verändern können.

#### Warum ist das ein eigenes Plugin?
Welten müssen auf vielen Unterservern, auf denen jeweils unterschiedliche Plugins installiert sind, vor Veränderung geschützt werden. Das ist möglich, indem dieses Plugin auf jedem davon installiert wird. Dadurch müssen keine dritten Plugins diese Funktion (unterschiedlich gut) implementieren, sondern es gibt eine konsistente Lösung - mit Build-Modus.


## Befehle
#### `/build` (Berechtigung: `build.toggle`)
Schaltet den Build-Modus für den Spieler um, der den Befehl ausführt. Beim Aktivieren wird sein Inventar geleert und er wird in den Kreativmodus versetzt. Beim Deaktivieren wird sein Spielmodus wieder zurückgesetzt und sein Inventar wiederhergestellt.

#### `/restrictbuild [ true | false ]` (Berechtigung: `build.restrict`)
Schränkt den Zugriff auf den Build-Modus ein. Wenn als Wert `false` angegeben wird, können die berechtigten Spieler den Build-Modus wie gewohnt nutzen. Wenn als Wert `true` angegeben wird, können nicht mehr alle berechtigten Spieler den Build-Modus nutzen. Die Berechtigung, um die Einschränkung zu umgehen, ist `build.restrict`. Die Spieler, die diese Berechtigung nicht haben und momentan im Build-Modus sind, werden aus diesem gekickt.

## Event-Handling
Die meisten Event-Listener dieses Plugins sind mit der Priorität `LOWEST` versehen, das bedeutet, dass wichtigere Events zuerst abgefangen werden. Dadurch sollten Kollisionen mit anderen Plugins vermieden werden, die ebenfalls mit dem jeweiligen Event arbeiten und dieses eventuell verändern.

## Technische Details
#### Unterstützte Minecraft-Versionen
1.20 - 1.20.1

## BuildAPI
Dieses Plugin bietet eine API an, die es anderen Plugins ermöglicht, den Build-Modus für bestimmte Spieler abzufragen und zu setzen.
Die Klasse `de.korzhorz.build.BuildAPI` bietet dafür die folgenden Methoden an:
- `boolean isInBuildMode(Player player)`
- `void setBuildMode(Player player, boolean enable)`
