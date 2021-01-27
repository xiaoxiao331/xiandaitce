package tce.com;
public class TextSMS {
	private final String receiver;
	private final String sender;
	private final String time;
	private final String message;

	private TextSMS(Builder builder) {
		receiver = builder.receiver;
		sender = builder.sender;
		time = builder.time;
		message = builder.message;
	}

	public static class Builder {
		private final String receiver;
		private final String message;

		private String time = "";
		private String sender = "";

		public Builder(String receiver, String message) {
			if (null == receiver || null == message || receiver.isEmpty() || message.isEmpty())
				throw new IllegalArgumentException("receiver and message can't be empty!");
			this.receiver = receiver;
			this.message = message;
		}

		public Builder time(String val) {
			time = val;
			return this;
		}

		public Builder sender(String val) {
			sender = val;
			return this;
		}

		public TextSMS build() {
			return new TextSMS(this);
		}
	}

	public String getReceiver() {
		return receiver;
	}

	public String getSender() {
		return sender;
	}

	public String getTime() {
		return time;
	}

	public String getMessage() {
		return message;
	}

}
