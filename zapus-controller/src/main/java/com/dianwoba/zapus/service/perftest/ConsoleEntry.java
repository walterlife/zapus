package com.dianwoba.zapus.service.perftest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

import static com.dianwoba.zapus.common.util.NoOp.noOp;

/**
 * Console相关信息
 */
public class ConsoleEntry {

	private String ip;
	/**
	 * Console port number.
	 */
	private Integer port;
	private ServerSocket socket;

	/**
	 * Constructor.
	 *
	 * @param port port
	 */
	public ConsoleEntry(String ip, Integer port) {
		this.ip = ip;
		this.port = port;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ConsoleEntry other = (ConsoleEntry) obj;
		if (port == null) {
			if (other.port != null) {
				return false;
			}
		} else if (!port.equals(other.port)) {
			return false;
		}
		return true;
	}

	public String getIp() {
		return ip;
	}

	@Override
	public int hashCode() {
		int result = ip != null ? ip.hashCode() : 0;
		result = 31 * result + (port != null ? port.hashCode() : 0);
		return result;
	}

	public void occupySocket() throws IOException {
		InetAddress address = null;
		try {
			address = InetAddress.getByName(ip);

		} catch (Exception e) {
			noOp();
		}
		if (address != null) {
			socket = new ServerSocket(port, 50, address);
		} else {
			socket = new ServerSocket(port);
		}
	}

	public void releaseSocket() {
		if (socket != null) {
			try {
				socket.close();
			} catch (Exception e) {
				noOp();
			}
		}
	}
}
