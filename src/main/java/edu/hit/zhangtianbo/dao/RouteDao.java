package edu.hit.zhangtianbo.dao;
import edu.hit.zhangtianbo.domain.Route;
import java.util.List;


    public interface RouteDao {

        int findTotalCount(int cid, String rname);

        List<Route> findByPage(int cid, int start, int pageSize, String rname);
    }

