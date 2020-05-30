package io.cord3c.ssi.networkmap.adapter.repository;

import java.io.Writer;
import javax.servlet.http.HttpServletResponse;

import io.cord3c.ssi.api.vc.DIDDocument;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import net.corda.core.crypto.SecureHash;
import net.corda.core.serialization.SerializationContext;
import net.corda.core.serialization.SerializationDefaults;
import net.corda.core.serialization.SerializationFactory;
import net.corda.core.utilities.ByteSequence;

@UtilityClass
public class VCNetworkMapUtils {

	@SneakyThrows
	public static <T> T deserialize(Class<T> clazz, byte[] data) {
		SerializationFactory defaultFactory = SerializationFactory.Companion.getDefaultFactory();
		SerializationContext defaultContext = SerializationDefaults.INSTANCE.getRPC_CLIENT_CONTEXT();
		return defaultFactory.deserialize(ByteSequence.of(data), clazz, defaultContext);
	}

	@RequiredArgsConstructor
	@Data
	public static class SecureHashHolder {

		private final SecureHash id;

	}

	@SneakyThrows
	public static void writeDid(HttpServletResponse response, DIDDocument doc) {
		Writer writer = response.getWriter();
		doc.writePrettyJsonToWriter(writer);
		writer.close();

		response.setStatus(200);
		response.setContentType("application/json");
	}
}
