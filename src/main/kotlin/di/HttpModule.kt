package org.example.di
import dagger.Module
import dagger.Provides
import org.example.Resources.UserResource
import org.glassfish.grizzly.http.server.HttpServer
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory
import org.glassfish.jersey.server.ResourceConfig
import javax.inject.Named
import javax.inject.Singleton
import javax.ws.rs.core.UriBuilder

@Module
class HttpModule {

    @Provides
    fun provideResourceConfig(userResource: UserResource): ResourceConfig {
        return ResourceConfig().register(userResource)
    }

    @Provides
    fun provideHttpServer(
        @Named("server.host") host: String,
        @Named("server.port") port: Int,
        application: ResourceConfig
    ): HttpServer {
        val uri = UriBuilder.fromUri("http://$host").port(port).build()
        return GrizzlyHttpServerFactory.createHttpServer(uri, application,true)
    }
}
