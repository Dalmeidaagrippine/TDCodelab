# TDCodelab
Projet de programmation mobile sous Android Studio

#Description

~Cette application mobile nommée PokemonGo permet d'afficher une liste de Pokemon(964)
et le détails de chaque pokemon avec leur image,ability,characteristics,gender,nature,color bref tout ce qui est spécifique à chaque pokemon.
Mais à l'heure d'aujourd'hui je n'ai pas pu le faire j'ai du donc modifier mon projet afin d'avoir quand meme un certain résultat tout en continuant à travailler dessus afin d'avoir le résultat souhaité

A ce niveau dans l'application il y a une liste de pokemon(964) qui s'affiche et quand on clique sur chaque élément de la liste on l'image du pokemon et son nom.

#Fonctionnalités

-Une recyclerview pour afficher des éléments
-API Rest (https://pokeapi.co/v2 parce que c'est celle que j'ai choisi dans la documentation) pour récupérer les données des pokemon avec l'ID ou le nom
-API Gson (https://https://jsonformatter.curiousconcept.com) qui me permet de formater les données de mon API dans un format exploitable
-API Retrofit qui va utiliser l'API Rest pour récupérer les données
-Architecture MVC pour rendre le code plus structuré et plus facile à lire
-Stockage de données en cache

Je me suis inspirée de certains tutos notamment:

https://codelabs.developers.google.com/codelabs/build-your-first-android-app/#0 pour bien connaitre mon environnement de travail

http://www.vogella.com/tutorials/AndroidRecyclerView/article.html#exercise-using-recyclerview-in-a-new-android-application qui m'a aidé à faire ma liste RecyclerView

https://www.vogella.com/tutorials/Retrofit/article.html#exercise-a-first-retrofit-client qui m'a aidé à faire l'appel à un serveur (mon API Rest)

http://tutos-android-france.com/sharedpreferences/ pour le stockage de données


-Les images sur l'icone je l'ai récupéré sur google
