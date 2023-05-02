package com.jaino.network.datasource.dictionary

import com.jaino.network.model.dictionary.DrinkDataResponse
import com.jaino.network.remote.DictionaryService
import javax.inject.Inject

class GetDrinkListDataSourceImpl @Inject constructor(
    private val service : DictionaryService
): GetDrinkListDataSource{
    override suspend fun getDrinkList(): Result<List<DrinkDataResponse>> =
        /*runCatching {
            service.getDrinkList().data
        }.onFailure { error ->
            error.printStackTrace()
            Result.failure<List<DrinkDataResponse>>(error)
        }*/
        Result.success(
            listOf(
                DrinkDataResponse(
                    name = "타이거",
                    image = "https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fb1fd6d34-a1f1-4ae5-b570-c45c82324848%2F%25ED%2583%2580%25EC%259D%25B4%25EA%25B1%25B0.png?id=a62c94d2-4dde-445a-a871-1602a8640747&table=block&spaceId=367884e1-3a37-4a83-ab76-29d66750ccbf&width=600&userId=8910a70c-7822-41a2-8209-21c7104398bf&cache=v2",
                    dosu = 1.0,
                    volume = 100,
                    price = 233,
                    type = "맥주"
                ),
                DrinkDataResponse(
                    name = "펄리셔 피노누",
                    image = "https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F4c091b97-2bbf-470d-bf96-e35f9b9c3397%2F%25ED%258E%2584%25EB%25A6%25AC%25EC%2585%2594_%25ED%2594%25BC%25EB%2585%25B8%25EB%2588%2584%25EC%2595%2584.png?id=b99ec708-7323-4d28-8e9b-6687bc7526a5&table=block&spaceId=367884e1-3a37-4a83-ab76-29d66750ccbf&width=600&userId=8910a70c-7822-41a2-8209-21c7104398bf&cache=v2",
                    dosu = 1.0,
                    volume = 100,
                    price = 233,
                    type = "와인"
                ),
                DrinkDataResponse(
                    name = "아구아디아블로",
                    image = "https://www.notion.so/image/http%3A%2F%2Fwww.bestshop8866.com%2Fupload%2Fpro%2F1128.jpg?id=a98ff19b-d1c3-47f0-b212-a5e87148910e&table=block&spaceId=367884e1-3a37-4a83-ab76-29d66750ccbf&width=600&userId=8910a70c-7822-41a2-8209-21c7104398bf&cache=v2",
                    dosu = 1.0,
                    volume = 100,
                    price = 233,
                    type = "리큐어"
                ),
                DrinkDataResponse(
                    name = "잭 다니엘",
                    image = "https://www.notion.so/image/http%3A%2F%2Fwww.bestshop8866.com%2Fupload%2Fpro%2F645.jpg?id=81479b77-e08a-4a1c-9f12-9785dde29f61&table=block&spaceId=367884e1-3a37-4a83-ab76-29d66750ccbf&width=600&userId=8910a70c-7822-41a2-8209-21c7104398bf&cache=v2",
                    dosu = 1.0,
                    volume = 100,
                    price = 233,
                    type = "위스키"
                ),
                DrinkDataResponse(
                    name = "신애유자",
                    image = "https://www.notion.so/image/https%3A%2F%2Fd1gsfyl9twema8.cloudfront.net%2Ffiles%2Fsupplier%2F46%2Fdrinks%2F9566_Shinae_Yuja.png%3Fw%3D200?id=a967ed3d-9586-4a97-843b-e7f16281c082&table=block&spaceId=367884e1-3a37-4a83-ab76-29d66750ccbf&width=600&userId=8910a70c-7822-41a2-8209-21c7104398bf&cache=v2",
                    dosu = 1.0,
                    volume = 100,
                    price = 233,
                    type = "과실주"
                ),
                DrinkDataResponse(
                    name = "삼양춘 약주",
                    image = "https://www.notion.so/image/https%3A%2F%2Fd13k6qhny4cqv3.cloudfront.net%2Fmedia%2Fpublic%2F%25EC%2582%25BC%25EC%2596%2591%25EC%25B6%2598_%25EC%2595%25BD%25EC%25A3%25BC.png%3Fw%3D200?id=26697809-eded-4404-a92b-a758d3a0dea5&table=block&spaceId=367884e1-3a37-4a83-ab76-29d66750ccbf&width=600&userId=8910a70c-7822-41a2-8209-21c7104398bf&cache=v2",
                    dosu = 1.0,
                    volume = 100,
                    price = 233,
                    type = "약주"
                ),
                DrinkDataResponse(
                    name = "간바레 오또상",
                    image = "https://www.notion.so/image/https%3A%2F%2Fyoung.hyundai.com%2Fupload%2FCMS_NEWS_IMAGE%2F2018%2F05%2F24%2FCMS_NEWS_IMAGE_C1JAQ8iHZnNnMmtbMq6g.jpg?id=711993f4-a853-4159-975c-0bee84960316&table=block&spaceId=367884e1-3a37-4a83-ab76-29d66750ccbf&width=600&userId=8910a70c-7822-41a2-8209-21c7104398bf&cache=v2",
                    dosu = 1.0,
                    volume = 100,
                    price = 233,
                    type = "사케"
                ),
                DrinkDataResponse(
                    name = "참이슬",
                    image = "https://www.notion.so/image/https%3A%2F%2Fcdn.veluga.kr%2Ffiles%2Fsupplier%2F228%2Fdrinks%2F%25E1%2584%258E%25E1%2585%25A1%25E1%2586%25B7%25E1%2584%258B%25E1%2585%25B5%25E1%2584%2589%25E1%2585%25B3%25E1%2586%25AF%2520%25E1%2584%2592%25E1%2585%25AE%25E1%2584%2585%25E1%2585%25A6%25E1%2584%2589%25E1%2585%25B1%2520%25E1%2584%2589%25E1%2585%25A9%25E1%2584%258C%25E1%2585%25AE(JINRO%2520CHAMISUL%2520FRESH%2520SOJU)_%25E1%2584%2592%25E1%2585%25A1%25E1%2584%258B%25E1%2585%25B5%25E1%2584%2590%25E1%2585%25B3%25E1%2584%258C%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2585%25E1%2585%25A9(hitejinro).png?id=887debdb-10c0-4d45-9ca0-c7fff6572e90&table=block&spaceId=367884e1-3a37-4a83-ab76-29d66750ccbf&width=600&userId=8910a70c-7822-41a2-8209-21c7104398bf&cache=v2",
                    dosu = 1.0,
                    volume = 100,
                    price = 233,
                    type = "소주"
                ),
                DrinkDataResponse(
                    name = "우도 땅콩 전통주",
                    image ="https://www.notion.so/image/https%3A%2F%2Fassets.business.veluga.kr%2Fmedia%2Fpublic%2F%25E1%2584%258B%25E1%2585%25AE%25E1%2584%2583%25E1%2585%25A9%25E1%2584%2584%25E1%2585%25A1%25E1%2586%25BC%25E1%2584%258F%25E1%2585%25A9%25E1%2586%25BC%25E1%2584%258C%25E1%2585%25A5%25E1%2586%25AB%25E1%2584%2590%25E1%2585%25A9%25E1%2586%25BC%25E1%2584%258C%25E1%2585%25AE_Nyp2f3E.png?id=e088caa5-6e60-49b2-95cc-31ff5aab23a7&table=block&spaceId=367884e1-3a37-4a83-ab76-29d66750ccbf&width=600&userId=8910a70c-7822-41a2-8209-21c7104398bf&cache=v2",
                    dosu = 1.0,
                    volume = 100,
                    price = 233,
                    type = "막걸리"
                )
            )
        )
}