package com.chunyi.repository;
import com.chunyi.entity.Locate_log;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.sql.Time;
import java.util.List;

/**
 * Created by 黄春怡 on 2017/2/10.
 */
public interface Locate_logRepository  extends CrudRepository<Locate_log,Integer> {

    @Query("select log from Locate_log log where log.id=(select max(id) from Locate_log where employee_id=?1)")
    public Locate_log findLastReport(int employee_id);

    @Query("select log.id from Locate_log log where log.id in (select max(id) from Locate_log group by employee_id) ")
    public List<Integer> findAllLastReport();

    @Query("select log from Locate_log log where DATEDIFF( Second,log.end_time, ?1)>4 and log.id in ?2 ")
    public List<Locate_log> findNoReport(Time nowTime,List<Integer>log_ids);
}
