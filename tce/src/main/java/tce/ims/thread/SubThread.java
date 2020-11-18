package tce.ims.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tce.ims.service.IMSService;
import tce.ims.vo.CarRetailInfoSearchVO;
import tce.ims.vo.IMSEntity;


public class SubThread extends Thread {
	
	private static final Logger log = LoggerFactory.getLogger(SubThread.class);

	
    
	
	private CountDownLatch countDownLatch;
    private String serviceName;
    private IMSService imsService;
    private CarRetailInfoSearchVO carRetailInfoSearchVO;
    public static List<IMSEntity> imsEntityList = new LinkedList<>();
    
    public SubThread(String serviceName,CountDownLatch countDownLatch,IMSService imsService,CarRetailInfoSearchVO carRetailInfoSearchVO){
        this.serviceName = serviceName;
        this.countDownLatch = countDownLatch;
        this.imsService = imsService;
        this.carRetailInfoSearchVO = carRetailInfoSearchVO;
    }
     
    public void run() {

    	List<IMSEntity> imsEntitylistSub;
    	try {
        	switch(serviceName){
        	case "P" :
        		log.debug("Task [P] start..");
        		imsEntitylistSub = imsService.getTypeP(this.carRetailInfoSearchVO);
    			imsEntityList.addAll(imsEntitylistSub);
    			log.debug("Task [P] over..");
        		break;
        	case "D" :
        		log.debug("Task [D] start..");
        		imsEntitylistSub = imsService.getTypeD(this.carRetailInfoSearchVO);
    			imsEntityList.addAll(imsEntitylistSub);
    			log.debug("Task [D] over..");
        		break;
        	case "Y" :
        		log.info("Task [Y] start..");
        		imsEntitylistSub = imsService.getTypeY(this.carRetailInfoSearchVO);
    			imsEntityList.addAll(imsEntitylistSub);
    			log.debug("Task [Y] over..");
        		break;
        	case "U" :
        		log.debug("Task [U] start..");
        		imsEntitylistSub = imsService.getTypeU(this.carRetailInfoSearchVO);
    			imsEntityList.addAll(imsEntitylistSub);
    			log.debug("Task [U] over..");
        		break;
        	case "A" :
        		log.debug("Task [A] start..");
        		imsEntitylistSub = imsService.getTypeA(this.carRetailInfoSearchVO);
    			imsEntityList.addAll(imsEntitylistSub);
    			log.debug("Task [A] over..");
        		break;
        	case "T" :
        		log.debug("Task [T] start..");
        		imsEntitylistSub = imsService.getTypeT(this.carRetailInfoSearchVO);
    			imsEntityList.addAll(imsEntitylistSub);
    			log.debug("Task [T] over..");
        		break;
        	case "N" :
        		log.debug("Task [N] start..");
        		imsEntitylistSub = imsService.getTypeN(this.carRetailInfoSearchVO);
    			imsEntityList.addAll(imsEntitylistSub);
    			log.debug("Task [N] over..");
        		break;
        	case "S" :
        		log.debug("Task [S] start..");
        		imsEntitylistSub = imsService.getTypeS(this.carRetailInfoSearchVO);
    			imsEntityList.addAll(imsEntitylistSub);
    			log.debug("Task [S] over..");
        		break;
        	case "C" :
        		log.debug("Task [C] start..");
        		imsEntitylistSub = imsService.getTypeC(this.carRetailInfoSearchVO);
    			imsEntityList.addAll(imsEntitylistSub);
    			log.debug("Task [C] over..");
        		break;
        	case "Z" :
        		log.debug("Task [Z] start..");
        		imsEntitylistSub = imsService.getTypeZ(this.carRetailInfoSearchVO);
    			imsEntityList.addAll(imsEntitylistSub);
    			log.debug("Task [Z] over..");
        		break;
        	case "W" :
        		log.debug("Task [W] start..");
        		imsEntitylistSub = imsService.getTypeW(this.carRetailInfoSearchVO);
    			imsEntityList.addAll(imsEntitylistSub);
    			log.debug("Task [W] over..");
        		break;
        	}
            
        } finally {
            //线程结束时，将计时器减一
            countDownLatch.countDown();
        }
    }
}

