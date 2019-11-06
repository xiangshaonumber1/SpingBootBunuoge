package cn.xiangshao.bunuoge.serviceImpl;


import cn.xiangshao.bunuoge.Utils.ResponseResult;
import cn.xiangshao.bunuoge.entity.request.TblArticleInfo;
import cn.xiangshao.bunuoge.entity.response.TblArticleInfoResult;
import cn.xiangshao.bunuoge.mapper.ArticleInfoMapper;
import cn.xiangshao.bunuoge.service.ArticleInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ArticleInfoServiceImpl implements ArticleInfoService {

    @Autowired
    ArticleInfoMapper articleInfoMapper;

    @Override
    public ResponseResult add(TblArticleInfo tblArticleInfo) {
        articleInfoMapper.add(tblArticleInfo);
        return ResponseResult.Success();
    }

    @Override
    public ResponseResult delete(Map<String, Object> params) {
        articleInfoMapper.delete(params);
        return ResponseResult.Success();
    }

    @Override
    public ResponseResult deleteBatch(String[] articleIds){
        articleInfoMapper.deleteBatch(articleIds);
        return ResponseResult.Success();
    }

    @Override
    public ResponseResult update(Map<String,Object> params) {
        articleInfoMapper.update(params);
        return ResponseResult.Success();
    }

    @Override
    public ResponseResult updateBatch(String[] articleIds, String status) {
        articleInfoMapper.updateBatch(articleIds,status);
        return ResponseResult.Success();
    }

    @Override
    public ResponseResult get(Map<String, Object> params) {
        Page<TblArticleInfoResult> data = articleInfoMapper.findList(params);
        return ResponseResult.Success().setData(data);
    }

    @Override
    public ResponseResult findList(Map<String, Object> params) {
        int pageNum = (int) params.getOrDefault("pageNum",1);
        int pageSize = (int) params.getOrDefault("pageSize",10);
        PageHelper.startPage(pageNum,pageSize);
        Page<TblArticleInfoResult> data = articleInfoMapper.findList(params);
        return ResponseResult.Success().setData(data);
    }
}
