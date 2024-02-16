package com.hyundai.myexperience.ui.program_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.data.entity.Comment
import com.hyundai.myexperience.databinding.FragmentProgramCommentBinding
import com.hyundai.myexperience.ui.program_info.adapter.CommentAdapter

class ProgramCommentFragment : Fragment() {
    private var _binding: FragmentProgramCommentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgramCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val commentList = listOf(
            Comment(
                "이*호",
                "2024년 2월 16일",
                "결국 N Advanced까지 왔습니다.\n아직도 많이 부족하지만 꼭 도전하고 싶었습니다.\n늘 HMG DXC에서 새로운 과정 최초 도전은 기대->당황->성장->자신감 순서인데\nN Advanced쯤 오니 더 빠르고 부드럽게, 더 아반떼N을 잘 다루고 싶은 욕심이 넘쳐나게 되었습니다.\n여전히 배울게 많고 많이 부족하지만, 조금이나마 성장해 가는 스스로의 모습에 너무 행복하고 즐거웠습니다.\n소중한 피드백 영상과 피드백 내용은 계속해서 반복하여, 내년 더 성장하여 다음 단계로 나아가고 싶습니다!\n올 한해 모터스포츠라는 꿈같은 취미가 생겼습니다"
            ),
            Comment(
                "이*호",
                "2024년 2월 12일",
                "알차고 멋진 LV2를 경험하게 해주신 구본웅 인스트럭터님 감사드립니다.\n먼곳에서 갈때마다 무언가 아쉬움이 있었는데 이번에는 꽉 채워서 왔습니다.\n조만간 LV3 도전해보겠습니다...예약을 ^^"
            ),
            Comment(
                "이*호",
                "2024년 2월 10일",
                "어제 송 인스트럭터님 너무 감사했습니다.\n\n태안에서 좋은 경험 하고 왔습니다. 우리 조 였던 3분 너무 재밌게 잘 받았고~ 수고하셨어요"
            ),
            Comment("이*호", "2024년 2월 1일", "재밌었습니다."),
        )

        binding.rvComments.adapter = CommentAdapter(commentList)
        binding.rvComments.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}