package org.demo.plugin.exchange.base;

/**
 * 交换中心枚举
 * @author Developer
 *
 */
public final class ISNCEnum {
	/**
	 * @description 业务枚举
	 */
	public static enum BusinessEnum  {

		collaboration(1, "协同"),

		officialdoc(2, "公文"),

		bulletin(3, "公告"),
		
		news(4,"新闻"),
		
		archive(5, "文档"),

		organization(500, "组织机构");

		private int key;

		private String value;

		BusinessEnum(int key, String value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static String getDisplayName(int key) {
			return getEnumByKey(key).getValue();
		}

		public static BusinessEnum getEnumByKey(int key) {
			for (BusinessEnum e : BusinessEnum.values()) {
				if (e.getKey() == key) {
					return e;
				}
			}
			throw new IllegalArgumentException("未定义的枚举类型!key=" + key);
		}
	}
	
	/**
	 * 交换服务枚举
	 * @author Developer
	 *
	 */
	public static enum ExchangeActionEnum{
	    ExchangeSend(10100,"e-send","发送交换信息"),
	    ExchangeBack(10200,"e-back","发送回执信息"),
	    ExchangeSyn(10300,"e-syn","发送同步信息"),
	    ExchangeReceive(10400,"e-receive","接收交换信息"),
	    ExchangeReceiveBack(10500,"e-receiveback","接收回执信息"),
	    ExchangeReceiveSyn(10600,"e-receivesyn","接收同步信息"),
	    ServiceRecCount(20100,"s-rec-count","询问待接收信息数"),
	    ServiceRecBackCount(20200,"s-rec-back-count","询问待接收回执数"),
	    ServiceUpdateState(20400,"s-update-count","更新状态信息"),
	    ServiceRegedit(20500,"s-regedit","注册"),
	    ServiceOrg(20600,"s-org","组织同步"),
	    ServiceDownOrg(20700,"s-downorg","下载组织机构"),
	    ServiceUpdatePin(20800,"s-updatepin","更新密码"),
	    ServiceStateControl(20900,"s-statecontrol","状态维护"),
	    SerciceDocMsg(201000,"s-docmsg","公文反馈信息");
	    private int key;

        private String value;
        
        private String desc;

        ExchangeActionEnum(int key, String value, String d) {
            this.key = key;
            this.value = value;
            this.desc = d;
        }

        public int getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
        
        public String getDesc(){
            return desc;
        }

        public static String getDisplayName(int key) {
            return getEnumByKey(key).getValue();
        }

        public static ExchangeActionEnum getEnumByKey(int key) {
            for(ExchangeActionEnum e : ExchangeActionEnum.values()) {
                if(e.getKey() == key) {
                    return e;
                }
            }
            throw new IllegalArgumentException("未定义的枚举类型!key=" + key);
        }
        
        public static ExchangeActionEnum getEnumByValue(String value) {
            for(ExchangeActionEnum e : ExchangeActionEnum.values()) {
                if(e.getValue().equals(value)) {
                    return e;
                }
            }
            throw new IllegalArgumentException("未定义的枚举类型!value=" + value);
        }
	}
	
	/**
	 * 交换执行动作枚举
	 * @author Administrator
	 *
	 */
	public static enum ExchangeExecuteActionEnum{
		Execute_Action_Sendstandard(10000,"发送交换信息"),
		Execute_Action_Receviestandard(20000,"接收交换信息"),
		Execute_Action_Sendback(30000,"发送回执信息"),
		Execute_Action_Recevieback(40000,"接收回执信息"),
		Execute_Action_Sendorg(50000,"发送组织机构"),
		Execute_Action_Recevieorg(60000,"接收组织机构"),
		Execute_Action_Regedit(70000,"注册单位信息"),
		Execute_Action_Org(8000,"组织机构同步信息"),
		Execute_Action_collaborationVersion(9001,"协同处理版本验证");
		
		
		private int key;
		
		private String value;
		
		private ExchangeExecuteActionEnum(int k,String v){
			this.key = k;
			this.value = v;
		}
		
		
		public int getKey() {
			return key;
		}


		public String getValue() {
			return value;
		}


		public static ExchangeExecuteActionEnum getEnumByKey(int key) {
			for (ExchangeExecuteActionEnum e : ExchangeExecuteActionEnum.values()) {
				if (e.getKey() == key) {
					return e;
				}
			}
			throw new IllegalArgumentException("未定义的枚举类型!key=" + key);
		}
	}
}
