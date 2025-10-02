package undirectedgraph;

public class Romenia {

    public static Graph defineGraph() {

        Graph g = new Graph();
        // Define cities:
        g.addVertex("Oradea", 46.96426574254036, 21.815404890241624);
        g.addVertex("Zerind", 46.74907455636024, 21.58718432800293);
        g.addVertex("Bucharest", 44.341737819671636, 26.087756137380598);
        g.addVertex("Urziceni", 44.80219051125526, 26.620829339141846);
        g.addVertex("Arad", 46.17601144965172, 21.18883740250587);
        g.addVertex("Mehadia", 44.96806630836487, 22.31100215174675);
        g.addVertex("Neamt", 46.948584960575104, 26.429365649585723);
        g.addVertex("Iasi", 47.092246983957295, 27.7442935759449);
        g.addVertex("R. Vilcea", 45.18066420500755, 24.22603499967575);
        g.addVertex("Eforie", 44.06833285443306, 28.49203796564102);
        g.addVertex("Pitesti", 44.94001630900383, 24.794176745586395);
        g.addVertex("Timisoara", 45.71682139312744, 21.324136634759903);
        g.addVertex("Craiova", 44.39273689263344, 23.731026438121795);
        g.addVertex("Hirsova", 44.76117109951973, 27.97279633775711);
        g.addVertex("Vaslui", 46.57550071591377, 27.856243468666076);
        g.addVertex("Giurgiu", 43.764523432931895, 25.966417933397295);
        g.addVertex("Sibiu", 45.88013898427963, 24.104012071208953);
        g.addVertex("Dobreta", 44.75270983664513, 22.592058277826307);
        g.addVertex("Fagaras", 45.98317185486793, 24.89952214298248);
        g.addVertex("Lugoj", 45.73915346168518, 21.811129800519943);
        // Define routes:
        g.addEdge("Bucharest","Giurgiu");
        g.addEdge("R. Vilcea","Sibiu");
        g.addEdge("Iasi","Vaslui");
        g.addEdge("Iasi","Neamt");
        g.addEdge("Lugoj","Mehadia");
        g.addEdge("Arad","Timisoara");
        g.addEdge("Arad","Sibiu");
        g.addEdge("Bucharest","Fagaras");
        g.addEdge("Eforie","Hirsova");
        g.addEdge("Fagaras","Sibiu");
        g.addEdge("Bucharest","Urziceni");
        g.addEdge("Craiova","Pitesti");
        g.addEdge("Dobreta","Mehadia");
        g.addEdge("Hirsova","Urziceni");
        g.addEdge("Arad","Zerind");
        g.addEdge("Craiova","R. Vilcea");
        g.addEdge("Craiova","Dobreta");
        g.addEdge("Urziceni","Vaslui");
        g.addEdge("Lugoj","Timisoara");
        g.addEdge("Bucharest","Pitesti");
        g.addEdge("Pitesti","R. Vilcea");
        g.addEdge("Oradea","Zerind");
        g.addEdge("Oradea","Sibiu");
        // Define regions:
        g.addVertexSet("Banat");
        g.addVertexToSet("Banat","Mehadia");
        g.addVertexToSet("Banat","Timisoara");
        g.addVertexToSet("Banat","Lugoj");
        g.addVertexSet("Crisana");
        g.addVertexToSet("Crisana","Oradea");
        g.addVertexToSet("Crisana","Zerind");
        g.addVertexToSet("Crisana","Arad");
        g.addVertexSet("Dobrogea");
        g.addVertexToSet("Dobrogea","Eforie");
        g.addVertexToSet("Dobrogea","Hirsova");
        g.addVertexSet("Moldova");
        g.addVertexToSet("Moldova","Neamt");
        g.addVertexToSet("Moldova","Iasi");
        g.addVertexToSet("Moldova","Vaslui");
        g.addVertexSet("Muntenia");
        g.addVertexToSet("Muntenia","Bucharest");
        g.addVertexToSet("Muntenia","Urziceni");
        g.addVertexToSet("Muntenia","Giurgiu");
        g.addVertexSet("Oltenia");
        g.addVertexToSet("Oltenia","R. Vilcea");
        g.addVertexToSet("Oltenia","Pitesti");
        g.addVertexToSet("Oltenia","Craiova");
        g.addVertexToSet("Oltenia","Dobreta");
        g.addVertexSet("Transilvania");
        g.addVertexToSet("Transilvania","Sibiu");
        g.addVertexToSet("Transilvania","Fagaras");

        return g;
    }

}
