package com.chunyi.controller;
import com.chunyi.entity.Employee;
import com.chunyi.entity.EmployeeLoc;
import com.chunyi.entity.Ibeacon_device;
import com.chunyi.entity.Locate_log;
import com.chunyi.repository.Ibeacon_deviceRepository;
import com.chunyi.repository.Locate_logRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.chunyi.repository.EmployeeRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
 /*
 * Created by 黄春怡 on 2017/2/9.
 */
@RestController
@Component
public class ReviewController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private Ibeacon_deviceRepository ibeacon_devicePepository;

    @Autowired
    private Locate_logRepository locate_logRepository;

    //首页
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "这里是首页";
    }

    /*手机端上报位置，更新人员当前位置*/
    @ApiOperation(value="人员上报当前位置", notes="上报内容为人员电话，设备的mac地址")
    @RequestMapping(value = "/reportLocation/{phone}/{mac}")
    public String reportLocation(@PathVariable String phone,@PathVariable String mac){

        int location = 0;
        Employee e = this.employeeRepository.findByPhone(phone);
        Ibeacon_device ibeacon_device = this.ibeacon_devicePepository.findByMac(mac);
        //没有此设备时，设置当前位置为空,有该设备时设置当前位置为设备编号
        if(ibeacon_device != null){
           location = ibeacon_device.getId();
        }
        if(e != null){
            e.setLocation(location);
            this.employeeRepository.save(e);
        }else{
            return "没有查询到任何职工信息！";
        }

        //更新历史定位数据库
        //找出上报人员在历史定位记录里面的最后一条记录
        Locate_log log = this.locate_logRepository.findLastReport(e.getId());

        //当前日期xxxx-xx-xx
        java.util.Date nDate = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date nowDate = java.sql.Date.valueOf(sdf.format(nDate));  //转型成java.sql.Date类型
        //当前时间hh:mm:ss
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        java.sql.Time nowTime = java.sql.Time.valueOf(format.format(nDate));

        //如果存在该职工的日志记录，则判断他最后一次上报的位置是不是本次上报的位置，
        //如果是，则添加结束时间，如果不是，则新建一条记录
        //如果日志中没有该职工上报的记录，则新建一条记录
        if(log == null || log.getDevice_id() != location){

            //创建一个日志对象
            Locate_log newLog = new Locate_log(e.getId(), location,nowDate, nowTime, nowTime);
            this.locate_logRepository.save(newLog);
            return "成功添加一条新纪录！";
        }else{
            log.setEnd_time(nowTime);
            this.locate_logRepository.save(log);
            return "成功更新人员位置！";
        }
    }

    /*查看历史定位日志*/
    @ApiOperation(value="获取历史定位日志", notes="")
    @RequestMapping(value = "reviewAllLog",method = RequestMethod.GET)
    public List<Locate_log> reivewLog(){
        List<Locate_log> logs = new ArrayList<Locate_log>();
        logs = (List<Locate_log>)this.locate_logRepository.findAll();
        return logs;
    }

     /*获取所有人员信息*/
    @ApiOperation(value="获取人员信息，包括人员所在房间名称", notes="")
    @RequestMapping(value = "/reviewAllEmployee",method = RequestMethod.GET)
    public List<EmployeeLoc> reviewAllEmployee(){

        List<EmployeeLoc> allEmployeeLoc = new ArrayList<EmployeeLoc>();
        List<Employee>employees = ( List<Employee>)this.employeeRepository.findAll();

        for (Employee e:employees) {
            String roomname = this.ibeacon_devicePepository.getRoomnameById(e.getLocation());
            allEmployeeLoc.add(new EmployeeLoc(e,roomname));
        }
        return allEmployeeLoc;

    }



    @Scheduled(fixedDelay = 4000)
    private void updateLocation() {
        List<Locate_log> logs = new ArrayList<Locate_log>();
        List<Integer> log_ids = (List<Integer>) this.locate_logRepository.findAllLastReport();
        //当前时间hh:mm:ss
        java.util.Date nDate = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        java.sql.Time nowTime = java.sql.Time.valueOf(format.format(nDate));
        //如果日志不为空
        if (log_ids != null) {
            logs = this.locate_logRepository.findNoReport(nowTime, log_ids);
            if (logs != null) {
                List<Integer> employee_ids = new ArrayList<Integer>();
                //查找需要自动更新的人员id
                for (int i=0;i<logs.size();i++) {
                    //修改定位日志内容（end_time,device_id）
                    logs.get(i).setEnd_time(nowTime);
                    logs.get(i).setDevice_id(0);
                    employee_ids.add(logs.get(i).getEmployee_id());
                }
                //更新定位日志
                this.locate_logRepository.save(logs);
                //更新人员信息表
                List<Employee> employees = this.employeeRepository.findById(employee_ids);
                if (employees.size() > 0) {

                    for (Employee ee : employees) {
                        Employee e = this.employeeRepository.findByPhone(ee.getPhone());
                        if(e != null){
                            e.setLocation(0);
                            this.employeeRepository.save(e);
                        }else{
                            System.out.println("没有查询到任何职工信息！");
                        }
                    }
                }else{
                    System.out.println("暂时没有信息需要自动更新！");
                }
            }
        }


    }








}
