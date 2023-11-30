package com.ruoyi.appoint.evaluation.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.appoint.evaluation.domain.Evaluation;
import org.apache.ibatis.annotations.Param;

/**
 * 预约评价Mapper接口
 * 
 * @author likang
 * @date 2023-03-11
 */
public interface EvaluationMapper
{
    /**
     * 查询预约评价
     * 
     * @param id
     *            预约评价主键
     * @return 预约评价
     */
    public Evaluation selectEvaluationById(Long id);

    /**
     * 查询预约评价列表
     * 
     * @param evaluation
     *            预约评价
     * @return 预约评价集合
     */
    public List<Evaluation> selectEvaluationList(Evaluation evaluation);

    /**
     * 新增预约评价
     * 
     * @param evaluation
     *            预约评价
     * @return 结果
     */
    public int insertEvaluation(Evaluation evaluation);

    /**
     * 修改预约评价
     * 
     * @param evaluation
     *            预约评价
     * @return 结果
     */
    public int updateEvaluation(Evaluation evaluation);

    /**
     * 删除预约评价
     * 
     * @param id
     *            预约评价主键
     * @return 结果
     */
    public int deleteEvaluationById(Long id);

    /**
     * 批量删除预约评价
     * 
     * @param ids
     *            需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEvaluationByIds(String[] ids);

    public List<Evaluation> queryEvaluationList(Map<String, Object> map);

    public int deleteEvaluationByProjectid(String projectId);
}
