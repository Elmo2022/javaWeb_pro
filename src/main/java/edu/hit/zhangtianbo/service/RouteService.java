package edu.hit.zhangtianbo.service;
import edu.hit.zhangtianbo.domain.Route;
import edu.hit.zhangtianbo.domain.PageBean;
public interface RouteService {
    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);
}