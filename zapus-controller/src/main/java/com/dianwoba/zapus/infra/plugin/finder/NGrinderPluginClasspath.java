package com.dianwoba.zapus.infra.plugin.finder;

import ro.fortsoft.pf4j.PluginClasspath;

/**
 * pf4j plugin path settings.
 *
 * @author Gisoo Gwon ,GeunWoo Son
 * @since 3.0
 */
public class NGrinderPluginClasspath extends PluginClasspath {

	@Override
	protected void addResources() {
		classesDirectories.add("");
		libDirectories.add("");
	}

}
