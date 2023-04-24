package edu.hit.zhangtianbo.service.impl;

import edu.hit.zhangtianbo.domain.PageBean;
import edu.hit.zhangtianbo.service.RouteService;
import edu.hit.zhangtianbo.domain.Route;
import edu.hit.zhangtianbo.dao.RouteDao;
import edu.hit.zhangtianbo.dao.impl.RouteDaoImpl;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        /*
        *   private int totalCount;//总记录数
            private int totalPage;//总页数
            private int currentPage;//当前页码
            private int pageSize;//每页显示的条数
            private List<T> list;//每页显示的数据集合
        * */
        PageBean<Route> pb = new PageBean<>();

        // 设置当前页、页总数
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        // 获得总记录数
        RouteDaoImpl routeDao  =new RouteDaoImpl();

        int totalCount = routeDao.findTotalCount(cid, rname);
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * pageSize;
        pb.setList(routeDao.findByPage(cid, start, pageSize, rname));
        // 判断是否整除
        if(totalCount % pageSize == 0) {
            pb.setTotalPage(totalCount / pageSize);
        }else {
            pb.setTotalPage(totalCount / pageSize + 1);
        }

        return pb;
    }
}
