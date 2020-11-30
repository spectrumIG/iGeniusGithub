package it.playground.igeniusgithub.domain.repository

import com.apollographql.apollo.ApolloClient
import it.playground.igeniusgithub.utility.FakeLocalDataSource
import it.playground.igeniusgithub.utility.FakeRemoteDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DefaultRepositoryTest {
    private val fakeRemoteDataSource = FakeRemoteDataSource()
    private val fakeLocalDataSource = FakeLocalDataSource()
    private val defaultRepository = DefaultRepository(fakeRemoteDataSource, fakeLocalDataSource, ApolloClient.builder(), OkHttpClient.Builder())


    @Test
    fun `verifyPossibleNotPresentTokenIsDownloadedAllParameterAreCorrect`() {
        runBlocking {
            val retrieveTokenAndStoreIt =
                defaultRepository.retrieveTokenAndStoreIt("clientIDCORRECT", "SoSoSecretVeryVeryLOOONG", "codeOhMyCodecodeAgainCode")
            Assert.assertEquals(retrieveTokenAndStoreIt.succeded, true)
        }
    }

    @Test
    fun `verifyPossibleNotPresentTokenIsDownloadedAllParameterAreWrong`() {
        runBlocking {
            val retrieveTokenAndStoreIt =
                defaultRepository.retrieveTokenAndStoreIt("client", "SoSoSecLOOONG", "codeOhMyCodecodeAgaicscacwqnCode")
            Assert.assertEquals(retrieveTokenAndStoreIt.failed, true)
        }
    }
}