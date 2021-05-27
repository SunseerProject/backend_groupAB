package jp.co.sunseer.sunseerservice.sunseerhht.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jp.co.sunseer.commonutils.R;
import jp.co.sunseer.sunseerservice.sunseerhht.entity.THiyarihatto;
import jp.co.sunseer.sunseerservice.sunseerhht.entity.vo.THiyarihattoQuery;
import jp.co.sunseer.sunseerservice.sunseerhht.service.THiyarihattoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author GroupA
 * @since 2021-05-20
 */
@Api(description = "ヒヤリハット管理システム")
@RestController
@RequestMapping("/sunseerhht/t-hiyarihatto")
public class THiyarihattoController {

    @Autowired
    private THiyarihattoService tHiyarihattoService;

    //1.全件取得する
    @ApiOperation(value = "案件全件表示")
    @GetMapping("findAll")
    public R findAllUser(){
        List<THiyarihatto> list = tHiyarihattoService.list(null);
        return R.ok().data("item",list);
    }

    //2.案件をロジック削除
    @ApiOperation(value = "IDより案件を削除")
    @DeleteMapping("deleteHiyarihatto/{id}")
    public R removeById(
            @ApiParam(name = "id", value = "媒体ID", required = true)
            @PathVariable String id){
        boolean flag = tHiyarihattoService.removeById(id);
        if(flag){
            return R.ok();
        } else {
            return R.error();
        }
    }

    //3.ページング
    //current カレントページ
    //limit ページ毎に表示数
    @ApiOperation(value = "ページング")
    @GetMapping("pageHiyarihatto/{current}/{limit}")
    public R pageListUsers(@PathVariable long current,
                           @PathVariable long limit) {
        //ページング　オブジェクト生成
        Page<THiyarihatto> pageTeacher = new Page<>(current,limit);

        //ページング実現
        tHiyarihattoService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();//Total数
        List<THiyarihatto> records = pageTeacher.getRecords(); //ページングデータを取得

        return R.ok().data("total",total).data("rows",records);
    }

    //4.条件付き、ページング
    @ApiOperation(value = "検索条件付きページング")
    @PostMapping("pageHiyarihattoCondition/{current}/{limit}")
    public R pageHiyarihattoCondition(@PathVariable long current,@PathVariable long limit,
                                @RequestBody(required = false) THiyarihattoQuery tHiyarihattoQuery) {
        //ページングオブジェクト生成
        Page<THiyarihatto> pageTeacher = new Page<>(current,limit);

        //条件作る
        QueryWrapper<THiyarihatto> wrapper = new QueryWrapper<>();

        // 条件の組み合わせ
        String targetSystem = tHiyarihattoQuery.getTargetSystem();
        String accidentClassification = tHiyarihattoQuery.getAccidentClassification();
        Integer level = tHiyarihattoQuery.getLevel();
        LocalDate date = tHiyarihattoQuery.getDate();
        LocalDate countermeasureDeadline = tHiyarihattoQuery.getCountermeasure_deadline();
        LocalDate countermeasureDeadlineStart = tHiyarihattoQuery.getCountermeasure_deadlineStart();
        LocalDate countermeasureDeadlineEnd = tHiyarihattoQuery.getCountermeasure_deadlineEnd();
        String status = tHiyarihattoQuery.getStatus();
        String staff = tHiyarihattoQuery.getStaff();
        LocalDate registrationDate = tHiyarihattoQuery.getRegistrationDate();
        LocalDate updateDate = tHiyarihattoQuery.getUpdateDate();
        LocalDate dateStart = tHiyarihattoQuery.getDateStart();
        LocalDate dateEnd = tHiyarihattoQuery.getDateEnd();

        //条件はNULLかを判断して、NULLではない場合、条件付き
        if(!StringUtils.isEmpty(targetSystem)) {
            wrapper.eq("target_system",targetSystem);
        }
        if(!StringUtils.isEmpty(accidentClassification)) {
            wrapper.like("accident_classification",accidentClassification);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(date)) {
            wrapper.eq("date",date);
        }
        if(!StringUtils.isEmpty(dateStart)&&!StringUtils.isEmpty(dateEnd)) {
            wrapper.between("date",dateStart,dateEnd);
        }
        if(!StringUtils.isEmpty(countermeasureDeadline)) {
            wrapper.eq("countermeasure_deadline",countermeasureDeadline);
        }
        if(!StringUtils.isEmpty(countermeasureDeadlineStart)&&!StringUtils.isEmpty(countermeasureDeadlineEnd)) {
            wrapper.between("countermeasure_deadline",countermeasureDeadlineStart, countermeasureDeadlineEnd);
        }
        if(!StringUtils.isEmpty(status)) {
            wrapper.eq("status",status);
        }
        if(!StringUtils.isEmpty(staff)) {
            wrapper.eq("staff",staff);
        }
        if(!StringUtils.isEmpty(registrationDate)) {
            wrapper.eq("registrationDate",registrationDate);
        }
        if(!StringUtils.isEmpty(updateDate)) {
            wrapper.eq("updateDate",updateDate);
        }

        //ソート
        wrapper.orderByDesc("date");

        //条件検索
        tHiyarihattoService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//トータルレコード数
        List<THiyarihatto> records = pageTeacher.getRecords(); //データList
        return R.ok().data("total",total).data("rows",records);
    }

    //5.IDで案件を検索
    @ApiOperation(value = "IDよりヒヤリハット案件を取得")
    @GetMapping("getHiyarihatto/{id}")
    public R getHiyarihattoByID(@PathVariable String id) {

        THiyarihatto tHiyarihatto = tHiyarihattoService.getById(id);
        return R.ok().data("tHiyarihatto",tHiyarihatto);
    }

    //6.案件の追加及び更新
    @ApiOperation(value = "ヒヤリハット案件追加及び更新")
    @PostMapping("addUpdateHiyarihatto")
    public R addUpdateHiyarihatto(@RequestBody THiyarihatto tHiyarihatto) {
        boolean save = tHiyarihattoService.saveOrUpdate(tHiyarihatto);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

