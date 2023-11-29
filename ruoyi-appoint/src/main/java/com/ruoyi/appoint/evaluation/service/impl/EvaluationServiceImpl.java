package com.ruoyi.appoint.evaluation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.appoint.evaluation.domain.Evaluation;
import com.ruoyi.appoint.evaluation.mapper.EvaluationMapper;
import com.ruoyi.appoint.evaluation.service.IEvaluationService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;

/**
 * 预约评价Service业务层处理
 * 
 * @author likang
 * @date 2023-03-11
 */
@Service
public class EvaluationServiceImpl implements IEvaluationService
{
    @Autowired
    private EvaluationMapper evaluationMapper;

    /**
     * 查询预约评价
     * 
     * @param id
     *            预约评价主键
     * @return 预约评价
     */
    @Override
    public Evaluation selectEvaluationById(Long id) {
        return evaluationMapper.selectEvaluationById(id);
    }

    /**
     * 查询预约评价列表
     * 
     * @param evaluation
     *            预约评价
     * @return 预约评价
     */
    @Override
    public List<Evaluation> selectEvaluationList(Evaluation evaluation) {
        return evaluationMapper.selectEvaluationList(evaluation);
    }

    /**
     * 新增预约评价
     * 
     * @param evaluation
     *            预约评价
     * @return 结果
     */
    @Override
    public int insertEvaluation(Evaluation evaluation) {
        evaluation.setCreateTime(DateUtils.getNowDate());
        return evaluationMapper.insertEvaluation(evaluation);
    }

    /**
     * 修改预约评价
     * 
     * @param evaluation
     *            预约评价
     * @return 结果
     */
    @Override
    public int updateEvaluation(Evaluation evaluation) {
        return evaluationMapper.updateEvaluation(evaluation);
    }

    /**
     * 批量删除预约评价
     * 
     * @param ids
     *            需要删除的预约评价主键
     * @return 结果
     */
    @Override
    public int deleteEvaluationByIds(String ids) {
        return evaluationMapper.deleteEvaluationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除预约评价信息
     * 
     * @param id
     *            预约评价主键
     * @return 结果
     */
    @Override
    public int deleteEvaluationById(Long id) {
        return evaluationMapper.deleteEvaluationById(id);
    }

    @Override
    public List<Evaluation> queryEvaluationList(Map<String, Object> map) {
        return evaluationMapper.queryEvaluationList(map);
    }

    @Override
    public int deleteEvaluationByProjectid(String projectId) {
        return evaluationMapper.deleteEvaluationByProjectid(projectId);
    }
}
