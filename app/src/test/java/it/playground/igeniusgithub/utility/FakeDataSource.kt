package it.playground.igeniusgithub.utility

import it.playground.igeniusgithub.domain.model.Result
import it.playground.igeniusgithub.domain.repository.DataSource
import kotlinx.coroutines.delay

class FakeLocalDataSource() : DataSource {
    val tokens = listOf("fasdfasdfasdfasdfasd", "uglyuglyuglyuglyuglyugly", "correctcorrectcorrect")
    override suspend fun askForToken(clientId: String?, clientSecret: String?, code: String?): Result<String> {
        val random = tokens.random()
        return if(random == "correctcorrectcorrect") {
            Result.Success(random)
        } else {
            Result.Error(Exception("bad bad error!"))
        }
    }

    //Not useful in this case. We could implement it but we will end into testing a part of
    // the SDK so it's not worth it.
    override suspend fun saveTokenLocally(code: String) {}
}

class FakeRemoteDataSource : DataSource {

    private val fakeClientID = "clientIDCORRECT"
    private val fakeClientSecret = "SoSoSecretVeryVeryLOOONG"
    private val fakeCode = "codeOhMyCodecodeAgainCode"

    override suspend fun askForToken(clientId: String?, clientSecret: String?, code: String?): Result<String> {
        if(clientId == fakeClientID && clientSecret == fakeClientSecret && code == fakeCode) {
            delay(300) //fake net delay
            return Result.Success("correctcorrectcorrect")
        } else {
            return Result.Error(Exception("wrong everything"))
        }
    }

    override suspend fun saveTokenLocally(code: String) {
        TODO("Not yet implemented")
    }


}