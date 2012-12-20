package fr.enst.transports;

import java.util.List;

import Metro.Dijkstra;
import Metro.IGraph;
import Metro.Previous;
import Metro.VertexInterface;

public class Chemin {

	private List<VertexInterface> shortestPath;
	public List<VertexInterface> getShortestPath(){
		return shortestPath;
	}
	public Chemin(String[] args, IGraph metro){
		if(args.length<=0){
			System.err.println("Enter at least an argument!");
			System.exit(0);
		}
		//Verify if args exist in the GraphMetro
		for(int i=1;i<args.length;i++){
			if(!(metro.getStationsMap().containsKey(args[i]))){
				System.out.println(args[i]+" is not a station");
				System.exit(0);
			}
		}

		//search excepted stations
		//ArrayList<VertexInterface> exceptedStations = new ArrayList<VertexInterface>();
		if(args.length>3){
			for(int i=3;i<args.length;i++){
				//verify if the station exists
				if(metro.getStationsMap().containsKey(args[i]))
					if(!args[i].equals(args[1]) && !args[i].equals(args[2]))
						metro.getStationsMap().get(args[i]).setReachable(false);
						//exceptedStations.add(metro.getStationsMap().get(args[i]));
					else{
						System.out.println("The request is not coherent. Try another path.");
						System.exit(0);
					}
			}
		}
		//Delete station and relink with other stations
		/*for(VertexInterface exceptedStation : exceptedStations){
			metro.removeVertices(exceptedStation);
		}
		//*/

		//Create every path from departure to stations
		VertexInterface departure = metro.getStationsMap().get(args[1]);
		Previous previous = Dijkstra.dijkstra(metro,departure);
		
		//search the shortest path
		shortestPath = previous.getShortestPath(metro.getStationsMap().get(args[2]));

		//Print path from departure to arrival
		String printPath = new String();
		int pathWeigh=0;
		int i=-1;
		for(VertexInterface pathPart : shortestPath){
			printPath+="\n"+pathPart.getLabel();
			if(i<previous.getWeight().size()-1){
				pathWeigh += previous.getWeight().get(i+1);
			}
			i++;
		}
		//pathWeigh-= previous.getWeight().get(i+1);
		String message2 = new String();
		if(args.length>3){
			String except=new String();
			for(int k=3;k<args.length;k++){
				if(k<args.length-1)
					except+=args[k]+", ";
				else
					except+=args[k];
				message2+=except;
			}
			message2=" without passing by "+message2;
		}
		
		if(i<=0){
			String message = "No existing path from \""+args[1]+"\" to \""+args[2]+"\"";
			String except;
			if(args.length>3)
				except=new String();
			System.out.println(message+message2+".");
		}
		else{
			System.out.println(printPath);
			System.out.println("Distance : "+pathWeigh+"mm");
			//if(args.length<4)
				System.out.println("Number of stations from \""+args[1]+"\" to \""+args[2]+"\""+message2+" : "+i);
		}
	}
}