/*
 * Copyright 2018 BotsCrew
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.botscrew.messengercdk.model.outgoing.message;

import com.botscrew.messengercdk.model.outgoing.attachment.Attachment;
import com.botscrew.messengercdk.model.outgoing.element.quickreply.QuickReply;
import com.botscrew.messengercdk.model.outgoing.message.QuickReplyMessage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GenericTemplateMessage extends QuickReplyMessage {
    private Attachment attachment;

    public GenericTemplateMessage(Attachment attachment) {
        super(null, null);
        this.attachment = attachment;
    }

    public GenericTemplateMessage(List<QuickReply> quickReplies, Attachment attachment) {
        super(null, quickReplies);
        this.attachment = attachment;
    }
}
