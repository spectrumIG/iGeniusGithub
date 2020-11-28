package it.playground.igeniusgithub.domain.model.usecase

/*
* Interface for all my UseCase
* */
interface UseCase{
    suspend operator fun invoke()
}