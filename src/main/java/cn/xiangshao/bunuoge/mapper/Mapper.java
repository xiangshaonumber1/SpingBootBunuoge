package cn.xiangshao.bunuoge.mapper;
import com.github.pagehelper.Page;
import java.util.Map;

public interface Mapper<T,L> {

    void add(T entity);

    void delete(Map<String, Object> params);

    void update(Map<String, Object> params);

    Page<L> findList(Map<String, Object> params);

}
