module LeiTunes23 {
	
	exports domain.facade;
	exports domain.core;
	exports domain.playlists;

	requires transitive java.desktop;
	requires java.logging;
	requires org.junit.jupiter.api;
	requires jl101;
	requires mp3agic;
	requires swt;
}