import com.example.composebase.base.IBaseAppConfig

object AppConfig: IBaseAppConfig {
    override fun getRemoteConfigDefaultValues(): Map<String, Any> = emptyMap()
}