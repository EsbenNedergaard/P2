package BackEnd.Pathfinding.RouteFinders;

import BackEnd.Geometry.LowestDistanceToEndComparator;
import BackEnd.Geometry.LowestTotalDistanceComparator;
import BackEnd.Geometry.PickingPoint;
import BackEnd.Pathfinding.PickingRoute;

import java.util.ArrayList;
import java.util.List;

public class ShortestRouteFinder implements RouteFinder {

    @Override
    public void reset() {

    }

    @Override
    public PickingRoute calculateRoute(List<PickingPoint> pickingList) {
        //this.pathFinder.changeComparator(new LowestDistanceToEndComparator());


        //this.pathFinder.changeComparator(new LowestTotalDistanceComparator());
        return null;
    }

    private List<PickingPoint> findShortestPickList(List<PickingPoint> pickingPoints){
        List<PickingPoint> pickingPointList = new ArrayList<>();





        return pickingPointList;
    }
}
