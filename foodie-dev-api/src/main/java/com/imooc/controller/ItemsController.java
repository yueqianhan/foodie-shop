package com.imooc.controller;


import com.imooc.pojo.*;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.ItemsVO;
import com.imooc.service.ItemService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.imooc.controller.BaseController.COMMON_PAGE_SIZE;

@Api(value = "商品接口", tags = {"商品信息展示相关接口"})
@RestController
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public IMOOCJSONResult info(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @PathVariable String  itemId){
        if (itemId == null){
            return  IMOOCJSONResult.errorMsg(null);
        }
        Items item =itemService.queryItemsById(itemId);
        List<ItemsImg> itemsImgList = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemsSpecList = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);
        ItemsVO itemsVO =new ItemsVO();
        itemsVO.setItem(item);
        itemsVO.setItemImgList(itemsImgList);
        itemsVO.setItemParams(itemsParam);
        itemsVO.setItemSpecList(itemsSpecList);
        return IMOOCJSONResult.ok(itemsVO);
    }

    @ApiOperation(value = "查询商品评价等级", notes = "查询商品评价等级", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public IMOOCJSONResult commentLevel(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @RequestParam String  itemId){
        if (itemId == null){
            return  IMOOCJSONResult.errorMsg("商品id不能为空");
        }
        CommentLevelCountsVO commentLevelCountsVO = itemService.queryCommentCounts(itemId);
        return IMOOCJSONResult.ok(commentLevelCountsVO);
    }

    @ApiOperation(value = "查询商品评论", notes = "查询商品评论", httpMethod = "GET")
    @GetMapping("/comments")
    public IMOOCJSONResult comments(
            @ApiParam(name = "itemId", value = "商品id", required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level", value = "评价等级", required = false)
            @RequestParam Integer level,
            @ApiParam(name = "page", value = "查询下一页的第几页", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "分页的每一页显示的条数", required = false)
            @RequestParam Integer pageSize) {

        if (StringUtils.isBlank(itemId)) {
            return IMOOCJSONResult.errorMsg(null);
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }

        PagedGridResult grid = itemService.queryPagedComments(itemId,
                level,
                page,
                pageSize);

        return IMOOCJSONResult.ok(grid);
    }

}
