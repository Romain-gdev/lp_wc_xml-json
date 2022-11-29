

Exercice 1 : XML bien formé
==========
```
ajout ligne 3 ==> <!DOCTYPE xml [
					  <!ENTITY xml "aaaaaaa">
					]>
						==> pour corriger la ligne 17 
						==><titre>Autour d'&xml;</titre>
					
ligne 13 ==> <auteur>Victor Hugo <editeur>Lacroix </auteur> et Verboeckhoven</editeur> 
  			==> <auteur>Victor Hugo   </auteur> 
    			<editeur>Lacroixet Verboeckhoven</editeur>
    			
ligne 15 ==> un autre livre -->
			==> <!-- un autre livre -->
    			
    			
    	
ligne 16 ==> <livre annee=1998 ISBN="2253096343">
			==> <livre annee="1998" ISBN="2253096343">
			

ligne 40 ajout ==>  </html>
```

Exercice 2 : XML valide via une DTD
==========

3. Repartir du fichier XML original et cette fois-ci modifier la DTD. Explorer. Chercher par exemple à ajouter un attribut obligatoire avec une valeur par défaut. Est-ce possible ? 
    - Oui en ajoutant un attribut dans `<!ATTLIST >` avec l'option `#REQUIRED`

4. Comment marque t on la séquence d'éléments ? l'alternative d'éléments ? Que signifie '*', '+', '?' ? Comment spécifier une valeur par défaut pour un attribut ? une valeur obligatoire ? 
    - valeurs par défaut et possibles entre parenthèses
    - valeur obligatoire via le `#REQUIRED`
    - '*' de 0 à infinie
    - '+' 1 ou plus
    - '?' facultatif

Exercice 3 : XML valide via un XSchema
==========

3. Repartir du fichier XML original et cette fois-ci modifier la XSD. Explorer. Chercher par exemple à ajouter à l'élément `born` un attribut `age` qui ne peut être que de 1 à 3 digits. Même question mais cette fois-ci un élément et non un attribut. Définissez un seul et même type. Tentez de rajouter un élément complexe avec une sructure 'choice' (age ou born par exemple)
    - Pour ce faire plusieurs méthodes sont possible soit nous imposons un min et un max comme ceci : 
    ```xsd
    <xs:attribute name="age">
		<xs:simpleType>
			<xs:restriction base="xs:integer">
				<xs:minInclusive value="0"/> 
				<xs:maxInclusive value="120"/>
			</xs:restriction>
		</xs:simpleType>
	</xs:attribute> 		
    ```
    - Soit nous pouvons limiter le nombre de digit ainsi que leur plage mais dans ce cas nous pouvons avoir un age de 999 par exemple : 
    ```xsd
    <xs:attribute name="age" type="xs:integer"> 
		<xs:simpleType>
            <xs:restriction base="xs:integer">
                <xs:pattern value="[0-9]{3}"/>
            </xs:restriction>
		</xs:simpleType>
	</xs:attribute>
    ```

4. Diagramme de classe
- Pour afficher le diagramme de classe cliquez [ici](exercice03/ClassDiagram.png)


Exercice 5 : Génération d'une API java à partir d'un XML Schéma
==========
- Pour cette exercice il suffit de cliquer [ici](exercice05/main/README) pour accéder au Readme du projet

Exercice 06 : quelques mots sur la structure d'un fichier JSON et sur les schémas json
===========

2. Quelle est la signification des accolades '{...}' et des parenthèses carrées '[...]' ? Donner un exemple d'élément XML converti ayant résulté en chacune de ces structures.
    - [...] correspond à un tableau de données, comme on peut le voir avec book qui est un tableau de contenant les informations sur les objets qui le compose
    - {...} correspond à un objet, notifiable sur les données de l'objet book ainsi que l'objet auteur
    - XML : 
    ```xml 
        <book id="0836217462" available="maybe">
            <isbn>0836217462</isbn>
            <title lang="pas français">Being a Dog Is a Full-Time Job</title>
            <author id="CMS">
                <name>Charles M Schulz</name>
                <born>1922-11-26</born>
                <dead>2000-02-12</dead>
            </author>
    ```
    - JSON : 
    ```JSON
      "book": [
      {
        "-id": "0836217462",
        "-available": "maybe",
        "isbn": "0836217462",
        "title": {
          "-lang": "pas français",
          "#text": "Being a Dog Is a Full-Time Job"
        },
        "author": {
          "-id": "CMS",
          "name": "Charles M Schulz",
          "born": "1922-11-26",
          "dead": "2000-02-12"
        },
    ```
    


Observer la conversion des éléments `titre`. Comment ont été convertis l'attribut et le contenu textuel ?
- XML : 
    ```xml         
        <title lang="pas français">Being a Dog Is a Full-Time Job</title>
    ```
- JSON : 
    ```JSON
        "title": {
          "-lang": "pas français",
          "#text": "Being a Dog Is a Full-Time Job"
        },
    ```
- L'on peut remarquer qu'en JSON l'attribut est transformé en :
    ```json
        "-attribut" : "valeur"
    ```
- Et le contenu textuel en : 
    ```json
        "#text" : "valeur"    
    ```

Quelle taille en ko font respectivement le fichier XML et le JSON correspondant ?
- XML : 9ko
- JSON : 11ko

Même question, quelle taille en ko font respectivement le fichier XML et le JSON correspondant ?
- XML : 8ko
- JSON : 7ko
Lequel est le plus verbeux ?
- Le XML est donc le language le plus verbeux après suppresion des caractères vides



Quelle est la dernière version de la spécification ? 
- 2020-12
Quelle est la version supportée par l'outil de conversion ?
- 2020-12

Outre le type "object", quels autres types identifiez-vous ? 
- On peut distinguer les types :
    - Array
    - String

Quelles propriétés sont des tableaux d'objets ? 
- Les propriétés sous forme de tableaux d'objets sont :
    - book
    - character

Par quels mécanismes est-il possible de spécifier les valeurs possibles d'un objet de type string ? Si de tels mécanismes existent les nommer et donner des exemples.
- Le mécanisme se fait par l'attribut Json `format` :
    ```json
    "born": {
                        "type": "string",
                        "format": "date"
                    }
    ```

Est-ce qu'un personnage `character` peut-être un auteur `author` (si tel est le cas donnez aussi le code qui illustre cela) ? 
- Le contenu peut être le même mais les objets restes `dissociés` l'un de l'autre