package co.com.mercado.libre.quasar.repository;

import co.com.mercado.libre.quasar.model.Satellite;
import co.com.mercado.libre.quasar.model.SatelliteSplit;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static co.com.mercado.libre.quasar.util.ApplicationConstants.*;

@Component
public class SatelliteRepository{

    public List<Satellite> findAll() {
        List<Satellite> satelliteList = new ArrayList<>();
        satelliteList.add(Satellite.builder().name(KENOBI_NAME).x(KENOBI_X_POSITION).y(KENOBI_Y_POSITION).build());
        satelliteList.add(Satellite.builder().name(SKYWALKER_NAME).x(SKYWALKER_X_POSITION).y(SKYWALKER_Y_POSITION).build());
        satelliteList.add(Satellite.builder().name(SATO_NAME).x(SATO_X_POSITION).y(SATO_Y_POSITION).build());
        return satelliteList;
    }

    public List<SatelliteSplit> getSatellitesSplitList(){
        List<SatelliteSplit> satelliteSplitList = new ArrayList<>();
        satelliteSplitList.add(SatelliteSplit.builder().name(KENOBI_NAME).active(false).build());
        satelliteSplitList.add(SatelliteSplit.builder().name(SKYWALKER_NAME).active(false).build());
        satelliteSplitList.add(SatelliteSplit.builder().name(SATO_NAME).active(false).build());
        return satelliteSplitList;
    }

    public  SatelliteSplit getSatelliteSplit(List<SatelliteSplit> satelliteSplitList, String name){
        for (SatelliteSplit satelliteSplit: satelliteSplitList
             ) {
            if(satelliteSplit.getName().equalsIgnoreCase(name))
                return satelliteSplit;
        }
        return null;
    }
}
