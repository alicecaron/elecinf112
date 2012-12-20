/*package Metro;

import java.util.ArrayList;

public class Dijkstra {

	public static IPrevious dijkstra(IGraph g, VertexInterface root){
		return dijkstra(g,new Pi(), new Previous(),new SetImpl(),root);
	}
	private static IPrevious dijkstra(IGraph g,IPi pi,IPrevious previous,ISet a,VertexInterface root){
		ArrayList<VertexInterface> allVertices = g.getAllVertices();
		int n = allVertices.size();
				
		a.add(root);
		for(VertexInterface x : allVertices)
			pi.setValue(x,Integer.MAX_VALUE);
		pi.setValue(root, 0);
		VertexInterface pivot = root;
		int piPivot = 0;
		for(int i=1;i<n;i++){
			ArrayList<VertexInterface> pivotNeighbours = (ArrayList<VertexInterface>) g.getNeighbours(pivot);
			for(VertexInterface y : pivotNeighbours){
				if(!a.contains(y)){
					if((piPivot+g.getWeight(pivot,y))<pi.getValue(y)){
						pi.setValue(y,piPivot+g.getWeight(pivot, y));
						previous.setValue(y, pivot);
					}
				}
			}
			VertexInterface newPivot = null;
			int piNewPivot = Integer.MAX_VALUE;
			for(VertexInterface v : allVertices){
				if(!a.contains(v)){
					int piV=pi.getValue(v);
					if(piV<piNewPivot){
						piNewPivot = piV;
						newPivot=v;
					}
				}
			}
			if(newPivot==null){
				return previous;
			}
			pivot = newPivot;
			piPivot = piNewPivot;
			a.add(pivot);
		}
		return previous;
	}
}
//*/

package Metro;

import java.util.ArrayList;

public class Dijkstra {

	public static Previous dijkstra(IGraph g, VertexInterface root){
		return dijkstra(g,new Pi(), new Previous(),new SetImpl(),root);
	}
	private static Previous dijkstra(IGraph g,IPi pi,Previous previous,ISet a,VertexInterface root){
		ArrayList<VertexInterface> allVertices = g.getAllVertices();
		int n = allVertices.size();
				
		a.add(root);
		for(VertexInterface x : allVertices)
			pi.setValue(x,Integer.MAX_VALUE);
		pi.setValue(root, 0);
		VertexInterface pivot = root;
		int piPivot = 0;
		for(int i=1;i<n;i++){
			ArrayList<VertexInterface> pivotNeighbours = (ArrayList<VertexInterface>) g.getNeighbours(pivot);
			for(VertexInterface y : pivotNeighbours){
				if(!a.contains(y)){
					if((piPivot+g.getWeight(pivot,y))<pi.getValue(y)){
						pi.setValue(y,piPivot+g.getWeight(pivot, y));
						previous.setValue(y, pivot);
						//((Previous) previous).setWeight(g.getWeight(pivot,y));
					}
				}
			}
			VertexInterface newPivot = null;
			int piNewPivot = Integer.MAX_VALUE;
			for(VertexInterface v : allVertices){
				if(!a.contains(v)){
					int piV=pi.getValue(v);
					if(piV<piNewPivot){
						piNewPivot = piV;
						newPivot=v;
					}
				}
			}
			if(newPivot==null){
				return previous;
			}
			pivot = newPivot;
			piPivot = piNewPivot;
			a.add(pivot);
		}
		return previous;
	}
}
