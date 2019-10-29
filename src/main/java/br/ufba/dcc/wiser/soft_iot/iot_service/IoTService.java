package br.ufba.dcc.wiser.soft_iot.iot_service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.ufba.dcc.wiser.soft_iot.entities.Device;
import br.ufba.dcc.wiser.soft_iot.entities.Sensor;
import br.ufba.dcc.wiser.soft_iot.entities.SensorData;
import br.ufba.dcc.wiser.soft_iot.local_storage.LocalDataController;
import br.ufba.dcc.wiser.soft_iot.mapping_devices.Controller;



@Path("/")

public class IoTService {
	
	private Controller fotDevices;
	private LocalDataController localDataController;
	
	public IoTService(){
		
	}
	
	@GET
    @Produces("application/json")
	@Path("devices")
	public Response getDevices() throws Exception {
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        try{
        	List<Device> devices = fotDevices.getListDevices();
            rb = Response.ok(devices);
        } catch (Exception e) {
        	e.printStackTrace();
            x.setStatus(true);
            x.setMessage(e.getMessage());
            rb = Response.ok(x);
        }
        
        return rb.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .header("Accept", "application/json")
                .allow("OPTIONS")
                .build();
    }
	
	@GET
    @Produces("application/json")
	@Path("devices/{device_id}")
	public Response getDevice(@PathParam("device_id") String deviceId) throws Exception {
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        try{
            rb = Response.ok(fotDevices.getDeviceById(deviceId));
        } catch (Exception e) {
        	e.printStackTrace();
            x.setStatus(true);
            x.setMessage(e.getMessage());
            rb = Response.ok(x);
        }
        
        return rb.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .header("Accept", "application/json")
                .allow("OPTIONS")
                .build();
    }
	
	@GET
    @Produces("application/json")
	@Path("devices/{device_id}/{sensor_id}")
	public Response getSensorData(@PathParam("device_id") String deviceId, @PathParam("sensor_id") String sensorId)
			throws Exception {
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        try{
        	Device device = fotDevices.getDeviceById(deviceId);
        	Sensor sensor = device.getSensorbySensorId(sensorId);
        	SensorData sensorData = localDataController.getLastSensorData(device, sensor);
            rb = Response.ok(sensorData);
        } catch (Exception e) {
        	e.printStackTrace();
            x.setStatus(true);
            x.setMessage(e.getMessage());
            rb = Response.ok(x);
        }
        
        return rb.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .header("Accept", "application/json")
                .allow("OPTIONS")
                .build();
    }
	
	@GET
    @Produces("application/json")
	@Path("devices/{device_id}/{sensor_id}/{start_datetime}/{end_datetime}")
	public Response getSensorDataByDateTime(@PathParam("device_id") String deviceId, @PathParam("sensor_id") String sensorId,
			@PathParam("start_datetime") String startDateTime, @PathParam("end_datetime") String endDateTime)
					throws Exception {
        ResponseBuilder rb;
        XmlErrorClass x = new XmlErrorClass();
        try{
        	Device device = fotDevices.getDeviceById(deviceId);
        	Sensor sensor = device.getSensorbySensorId(sensorId);
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        	Date startDate = sdf.parse(startDateTime);
        	Date endDate = sdf.parse(endDateTime);
        	List<SensorData> sensorData = localDataController.getSensorDataByDateTime(device, sensor, startDate, endDate);
            rb = Response.ok(sensorData);
        } catch (Exception e) {
        	e.printStackTrace();
            x.setStatus(true);
            x.setMessage(e.getMessage());
            rb = Response.ok(x);
        }
        
        return rb.header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .header("Accept", "application/json")
                .allow("OPTIONS")
                .build();
    }

	public void setFotDevices(Controller fotDevices) {
		this.fotDevices = fotDevices;
	}

	public void setLocalDataController(LocalDataController localDataController) {
		this.localDataController = localDataController;
	}
	
	

}
