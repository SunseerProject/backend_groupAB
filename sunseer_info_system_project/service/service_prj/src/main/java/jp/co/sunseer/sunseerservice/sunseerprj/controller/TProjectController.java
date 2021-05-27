package jp.co.sunseer.sunseerservice.sunseerprj.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jp.co.sunseer.commonutils.R;
import jp.co.sunseer.sunseerservice.sunseerprj.entity.TProject;
import jp.co.sunseer.sunseerservice.sunseerprj.entity.vo.UserQuery;
import jp.co.sunseer.sunseerservice.sunseerprj.service.TProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GroupB
 * @since 2021-05-25
 */
@Api(description = "案件管理システム")
@RestController
@RequestMapping("/sunseerprj/t-project")
public class TProjectController {
    @Autowired
    private TProjectService tProjectService;

    //1.全件取得する
    @ApiOperation(value = "案件全件")
    @GetMapping("findAll")
    public R findAllProject(){
        List<TProject> list = tProjectService.list(null);
        return R.ok().data("item",list);
    }

    //2.案件をロジック削除
    @ApiOperation(value = "IDより案件を削除")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "ユーザーID", required = true)
            @PathVariable String id){
        boolean flag = tProjectService.removeById(id);
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
    @GetMapping("pageProject/{current}/{limit}")
    public R pageListProjects(@PathVariable long current,
                           @PathVariable long limit) {
        //ページング　オブジェクト生成
        Page<TProject> pageTeacher = new Page<>(current,limit);

        //ページング実現
        tProjectService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();//Total数
        List<TProject> records = pageTeacher.getRecords(); //ページングデータを取得

        return R.ok().data("total",total).data("rows",records);
    }

    //4.条件付きページング
    @ApiOperation(value = "検索条件付きページング")
    @PostMapping("pageProjectCondition/{current}/{limit}")
    public R pageProjectsCondition(@PathVariable long current,@PathVariable long limit,
                                @RequestBody(required = false) UserQuery userQuery) {
        //ページングオブジェクト生成
        Page<TProject> pageTeacher = new Page<>(current,limit);

        //条件作る
        QueryWrapper<TProject> wrapper = new QueryWrapper<>();

        // 条件の組み合わせ
        String id = userQuery.getId();
        String project = userQuery.getProject();
        String case_name = userQuery.getCase_name();
        String development_manager = userQuery.getDevelopment_manager();
        LocalDate development_start_date = userQuery.getDevelopment_start_date();
        LocalDate s_in_finished_day = userQuery.getS_in_finished_day();
        String status = userQuery.getStatus();
        Double manhours_of_development = userQuery.getManhours_of_development();
        Double manhours_this_month = userQuery.getManhours_this_month();
        String remarks = userQuery.getRemarks();
        Integer progress = userQuery.getProgress();
        Integer DEL_FLG = userQuery.getDEL_FLG();
        LocalDate registration_date = userQuery.getRegistration_date();
        LocalDate update_date = userQuery.getUpdate_date();

        //条件はNULLかを判断して、NULLではない場合、条件付き
        if(!StringUtils.isEmpty(id)){
            wrapper.eq("id",id);
        }
        if(!StringUtils.isEmpty(project)){
            wrapper.like("project",project);
        }
        if(!StringUtils.isEmpty(case_name)){
            wrapper.like("case_name",case_name);
        }
        if(!StringUtils.isEmpty(development_manager)){
            wrapper.like("development_manager",development_manager);
        }
        if(!StringUtils.isEmpty(development_start_date)){
            wrapper.eq("development_start_date",development_start_date);
        }
        if(!StringUtils.isEmpty(s_in_finished_day)){
            wrapper.eq("s_in_finished_day",s_in_finished_day);
        }
        if(!StringUtils.isEmpty(status)){
            wrapper.eq("status",status);
        }
        if(!StringUtils.isEmpty(manhours_of_development)){
            wrapper.eq("manhours_of_development",manhours_of_development);
        }
        if(!StringUtils.isEmpty(manhours_this_month)){
            wrapper.eq("manhours_this_month",manhours_this_month);
        }
        if(!StringUtils.isEmpty(remarks)){
            wrapper.like("remarks",remarks);
        }
        if(!StringUtils.isEmpty(progress)){
            wrapper.like("progress",progress);
        }
        if(!StringUtils.isEmpty(DEL_FLG)){
            wrapper.eq("DEL_FLG",DEL_FLG);
        }
        if(!StringUtils.isEmpty(registration_date)){
            wrapper.eq("registration_date",registration_date);
        }
        if(!StringUtils.isEmpty(update_date)){
            wrapper.eq("update_date",update_date);
        }

        //ソート
        wrapper.orderByDesc("project");

        //条件検索
        tProjectService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//トータルレコード数
        List<TProject> records = pageTeacher.getRecords(); //データList
        return R.ok().data("total",total).data("rows",records);
    }

    //5.案件追加
    @ApiOperation(value = "案件情報追加")
    @PostMapping("addProject")
    public R addProject(@RequestBody TProject tProject) {
        System.out.println("================================");
//        System.out.println(tProject.getIsDeleted());
        boolean save = tProjectService.save(tProject);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //6.IDで案件を検索
    @ApiOperation(value = "IDより案件情報を取得")
    @GetMapping("getProject/{id}")
    public R getProjectByID(@PathVariable String id) {

        TProject tProject = tProjectService.getById(id);
        return R.ok().data("prjInfo",tProject);
    }

    //7.案件情報を更新
    @PostMapping("updateProject")
    public R updateProject(@RequestBody TProject tProject) {
        boolean flag = tProjectService.updateById(tProject);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //詳細検索
    @ApiOperation(value = "詳細検索")
    @PostMapping("pageDetailUserCondition/{current}/{limit}")
    public R pageDetailUsersCondition(@PathVariable long current, @PathVariable long limit,
                                      @RequestBody(required = false) UserQuery userQuery) {
        //ページングオブジェクト生成
        Page<TProject> pageTeacher = new Page<>(current,limit);

        //条件作る
        QueryWrapper<TProject> wrapper = new QueryWrapper<>();

        // 条件の組み合わせ
        String id = userQuery.getId();
        String project = userQuery.getProject();
        String case_name = userQuery.getCase_name();
        String development_manager = userQuery.getDevelopment_manager();
        LocalDate development_start_date = userQuery.getDevelopment_start_date();
        LocalDate s_in_finished_day = userQuery.getS_in_finished_day();
        String status = userQuery.getStatus();
        Double manhours_of_development = userQuery.getManhours_of_development();
        Double manhours_this_month = userQuery.getManhours_this_month();
        String remarks = userQuery.getRemarks();
        Integer progress = userQuery.getProgress();
        Integer DEL_FLG = userQuery.getDEL_FLG();
        LocalDate registration_date = userQuery.getRegistration_date();
        LocalDate update_date = userQuery.getUpdate_date();
        String firstDate = userQuery.getFirstDate();
        String lastDate = userQuery.getLastDate();
        String sInFirstDate = userQuery.getSInFirstDate();
        String sInLastDate = userQuery.getSInLastDate();

        //条件はNULLかを判断して、NULLではない場合、条件付き
        if(!StringUtils.isEmpty(id)){
            wrapper.eq("id",id);
        }
        if(!StringUtils.isEmpty(project)){
            wrapper.like("project",project);
        }
        if(!StringUtils.isEmpty(case_name)){
            wrapper.like("case_name",case_name);
        }
        if(!StringUtils.isEmpty(development_manager)){
            wrapper.like("development_manager",development_manager);
        }
        if(!StringUtils.isEmpty(development_start_date)){
            wrapper.eq("development_start_date",development_start_date);
        }
        if(!StringUtils.isEmpty(s_in_finished_day)){
            wrapper.eq("s_in_finished_day",s_in_finished_day);
        }
        if(!StringUtils.isEmpty(status)){
            wrapper.eq("status",status);
        }
        if(!StringUtils.isEmpty(manhours_of_development)){
            wrapper.eq("manhours_of_development",manhours_of_development);
        }
        if(!StringUtils.isEmpty(manhours_this_month)){
            wrapper.eq("manhours_this_month",manhours_this_month);
        }
        if(!StringUtils.isEmpty(remarks)){
            wrapper.like("remarks",remarks);
        }
        if(!StringUtils.isEmpty(progress)){
            wrapper.like("progress",progress);
        }
        if(!StringUtils.isEmpty(DEL_FLG)){
            wrapper.eq("DEL_FLG",DEL_FLG);
        }
        if(!StringUtils.isEmpty(registration_date)){
            wrapper.eq("registration_date",registration_date);
        }
        if(!StringUtils.isEmpty(update_date)){
            wrapper.eq("update_date",update_date);
        }


        //日付による絞り込み
        //開発開始日
        //範囲検索
        if (!StringUtils.isEmpty(firstDate) && !StringUtils.isEmpty(lastDate)){
            wrapper.between("development_start_date", firstDate, lastDate);
        }
        //その日以降
        else if(!StringUtils.isEmpty(firstDate) && StringUtils.isEmpty(lastDate)){
            wrapper.gt("development_start_date", firstDate);
        }
        //その日以前
        else if(StringUtils.isEmpty(firstDate)&& !StringUtils.isEmpty(lastDate)){
            wrapper.lt("development_start_date", lastDate);
        }
        //S-in完了日
        //範囲検索
        if (!StringUtils.isEmpty(sInFirstDate) && !StringUtils.isEmpty(sInLastDate)){
            wrapper.between("s_in_finished_day", sInFirstDate, sInLastDate);
        }
        //その日以降
        else if(!StringUtils.isEmpty(sInFirstDate) && StringUtils.isEmpty(sInLastDate)){
            wrapper.gt("s_in_finished_day", sInFirstDate);
        }
        //その日以前
        else if(StringUtils.isEmpty(sInFirstDate) && !StringUtils.isEmpty(sInLastDate)){
            wrapper.lt("s_in_finished_day", sInLastDate);
        }

        //ソート
        wrapper.orderByDesc("project");

        //条件検索
        tProjectService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//トータルレコード数
        List<TProject> records = pageTeacher.getRecords(); //データList
        return R.ok().data("total",total).data("rows",records);
    }
}

