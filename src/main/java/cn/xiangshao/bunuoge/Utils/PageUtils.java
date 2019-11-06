package cn.xiangshao.bunuoge.Utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class PageUtils {

    public static PageInfo generatePage(Supplier<List> supplier,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List list = supplier.get();
        return new PageInfo(list);
    }

    public static Page generatePage(Map<String,Object> params){
        int pageNum = Integer.parseInt(params.get("pageNum").toString());
        int pageSize = Integer.parseInt(params.get("pageSize").toString());
        return PageHelper.startPage(pageNum,pageNum);
    }
}
