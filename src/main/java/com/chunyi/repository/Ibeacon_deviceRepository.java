package com.chunyi.repository;

import com.chunyi.entity.Ibeacon_device;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
/**
 * Created by 黄春怡 on 2017/2/10.
 */
public interface Ibeacon_deviceRepository extends CrudRepository<Ibeacon_device,Integer>{

   @Query("select a from Ibeacon_device a where a.mac=?1")
    public Ibeacon_device findByMac(String mac);

   @Query("select roomname from Ibeacon_device where id=?1")
    public String getRoomnameById(int id);



}
